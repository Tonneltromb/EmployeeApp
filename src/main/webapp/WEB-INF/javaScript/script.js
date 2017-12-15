let positions = {};
let employees = {};

$(document).ready(function () {
    popUpHide();
    addPositionsToSelectOfPositions();
    hideErrorPanel();
    showEmployees();
    $('input').attr('required', 'true');
    $('#pass').attr('pattern', '[A-Z][0-9][0-9][0-9]');
    $('#year').attr('max', new Date().getFullYear()).attr('min', 1999);
    $('#month').attr('max', 12).attr('min', 1);
    $('#day').attr('max', 31).attr('min', 1);
    $('#position').attr('size', 1);

});

//Функция отрисовки tbody таблицы сотрудников
function showEmployees() {
    $.getJSON('/employees', function (data) {
        $.each(data, function (id, employee) {
            employees[employee.id] = employee;
            addEmployeeToTable(employee);
        });
    });
}

//Добавление сотрудника в таблицу
function addEmployeeToTable(employee) {
    let date = dateConverter(employee.dateOfEmployment);
    let tr = $('<tr></tr>').attr("id", employee.id);
    $('tbody').append(tr);
    tr.append("<td>" + employee.pass + "</td>" +
        "<td>" + employee.name + "</td>" +
        "<td>" + employee.lastName + "</td>" +
        "<td>" + positions[employee.positionId] + "</td>" +
        "<td>" + date + "</td>")
        .append('<td class="withButton"><button class="deleteButton">X</button></td>')
        .append('<td class="withButton"><button class="redactButton">Редактировать</button></td>');
}

//Конвертер даты в формат yyyy-mm-dd
function dateConverter(dateValue) {
    let date = new Date(dateValue);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    if (month < 10) {
        month = '0' + month;
    }
    let day = date.getDate();
    if (day < 10) {
        day = '0' + day;
    }
    return year + '-' + month + '-' + day;
}

//Показать окно с формой
function popUpShow() {
    $('#popUpContainer').show();
}

//Скрыть окно с формой
function popUpHide() {
    $('#popUpContainer').hide();
}

//Добавить позиции в список формы
function addPositionsToSelectOfPositions() {
    $.getJSON('/positions', function (data) {
        for (let i = 0; i < data.length; i++) {
            addSelectValue(data[i]);
            positions[data[i].id] = data[i].name;
        }
    });

    function addSelectValue(position) {
        let option = $('<option></option>')
            .attr('value', position.id)
            .text(position.name);
        $('#position').append(option);
    }
}

/*Обработчики кнопок*/
//Кнопка добавления сотрудника
$(".addButton").click(function () {
    hideErrorPanel();
    if($('#employeeId').val()){
        $('#employeeId').removeAttr('value');
    }
    $('input').val(null);
    $('#action').val('add');
    $('#year').val(new Date().getFullYear());
    $('#month').val(new Date().getMonth() + 1);
    $('#day').val(new Date().getDate());
    popUpShow();
});

//Кнопка обновления таблицы
$('.refreshButton').click(function () {
    $('tbody tr').remove();
    hideErrorPanel();
    showEmployees();
});

//Кнопка удаления сотрудника
$('table').on('click', '.deleteButton', function (event) {
    let tr = event.target.parentElement.parentElement;
    $.ajax({
        url: '/employees/remove/'+ tr.id,
        type: 'DELETE'
    })
        .done(function () {
            tr.remove();
        })
        .fail(function (jqXHR) {
            showError(jqXHR);
        })
});

//Кнопка редактирования сотрудника
$('table').on('click', '.redactButton', function (event) {
    hideErrorPanel();
    let tr = event.target.parentElement.parentElement;
    $('#action').val('edit');
    $('#employeeId').val(tr.id);
    let employee = employees[tr.id];
    let date = new Date(employee.dateOfEmployment);
    $('#name').val(employee.name);
    $('#lastName').val(employee.lastName);
    $('#position').val(employee.positionId);
    $('#pass').val(employee.pass);
    $('#year').val(date.getFullYear());
    $('#month').val(date.getMonth() + 1);
    $('#day').val(date.getDate());
    popUpShow();
});

//Кнопка отмены вызова формы
$('.escapeButton').click(function () {
    $('input').val(null);
    $('#position').val(0);
    if($('#employeeId').val()){
        $('#employeeId').removeAttr('value');
    }
    popUpHide();
});

//Заполнение формы данными "липового" сотрудника
$('#getMock').click(function () {
    $('#name').val('Липовый');
    $('#lastName').val("Сотрудник");
    $('#position').val(0);
    $('#pass').val("A000");
    $('#year').val(new Date().getFullYear());
    $('#month').val(new Date().getMonth() + 1);
    $('#day').val(new Date().getDate());
});

//Показать окно с ошибкой
function showError(jqXHR) {
    $('#errorPanel p').text('Ошибка сервера! Код ошибки: ' + jqXHR.status);
    $('#errorPanel').show();
}

//Скрыть окно с ошибкой
function hideErrorPanel() {
    $('#errorPanel').hide();
    $('#errorPanel p').text('');
}

//Отправка формы
$('#popUpForm').submit(function (event) {
    event.preventDefault();
    let year = +$('#year').val();
    let month = $('#month').val() - 1;
    let day = +$('#day').val();
    let date = new Date();
    date.setFullYear(year);
    date.setMonth(month);
    date.setDate(day);

    let id = $('#employeeId').val();
    let action = $('#action').val();
    let employee = {
        name: $('#name').val(),
        lastName: $('#lastName').val(),
        positionId: +$('#position').val(),
        pass: ($('#pass').val()),
        dateOfEmployment: date.getTime(),
        id: (id ? id : undefined)
    };

    $.ajax({
        url: '/employees/' + action,
        data: employee,
        type: 'POST'
    })
        .done(function (newEmployeeId) {
            if (action === 'add') {
                employee['id'] = newEmployeeId;
                addEmployeeToTable(employee);
                employees[newEmployeeId] = employee;
            } else {
                tr = $('tr#' + id + '');
                tr.children()[0].innerText = employee.pass;
                tr.children()[1].innerText = employee.name;
                tr.children()[2].innerText = employee.lastName;
                tr.children()[3].innerText = positions[employee.positionId];
                tr.children()[4].innerText = dateConverter(date);
            }
        })
        .fail(function (jqXHR) {
            showError(jqXHR);
        })
        .always(function () {
            hideErrorPanel();
            popUpHide();
        });
});