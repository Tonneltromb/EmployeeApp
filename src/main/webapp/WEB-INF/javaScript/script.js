let id;
let url;
let positions = {};
let employees = {};

$(document).ready(function () {
    popUpHide();
    addPositionsToSelectOfPositions();
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
    $.getJSON('/employees/showAll', function (data) {
        employees = data;
        $.each(data, function (id, employee) {
            addEmployeeToTable(+id, employee);
        });
    });
}

//Добавление сотрудника в таблицу
function addEmployeeToTable(id, employee) {
    let date = new Date(employee.dateOfEmployment)
        .toISOString()
        .substring(0, 10);
    let tr = $('<tr></tr>').attr("id", id);
    $('tbody').append(tr);
    tr.append("<td>" + employee.pass + "</td>" +
        "<td>" + employee.name + "</td>" +
        "<td>" + employee.lastName + "</td>" +
        "<td>" + positions[employee.positionId] + "</td>" +
        "<td>" + date + "</td>")
        .append('<td class="withButton"><button class="deleteButton">X</button></td>')
        .append('<td class="withButton"><button class="redactButton">Редактировать</button></td>');
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
    $('input').val(null);
    url = '/employees/add';
    $('#year').val(new Date().getFullYear());
    $('#month').val(new Date().getMonth() + 1);
    $('#day').val(new Date().getDate());
    popUpShow();
});

//Кнопка обновления
$('.refreshButton').click(function () {
    $('tbody tr').remove();
    showEmployees();
});

//Кнопка удаления сотрудника
$('table').on('click', '.deleteButton', function (event) {
    let tr = event.target.parentElement.parentElement;
    $.ajax({
        url: '/employees/remove',
        data: {id: tr.id},
        type: 'GET'
    })
        .done(function () {
            tr.remove();
        })
        .fail(function () {
            alert('Что-то пошло не так');
        })
});

//Кнопка редактирования сотрудника
$('table').on('click', '.redactButton', function (event) {
    url = '/employees/edit';
    let tr = event.target.parentElement.parentElement;
    id = tr.id;
    let empl = employees[id];
    let date = new Date(empl.dateOfEmployment)
        .toISOString()
        .substring(0, 10);
    date = date.split('-');

    $('#name').val(empl.name);
    $('#lastName').val(empl.lastName);
    $('#position').val(empl.positionId);
    $('#pass').val(empl.pass);
    $('#year').val(date[0]);
    $('#month').val(date[1]);
    $('#day').val(date[2]);

    popUpShow();
});

//Кнопка отмены вызова формы
$('.escapeButton').click(function () {
    $('input').val(null);
    $('#position').val(0);
    url = null;
    id = null;
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

//Отправка формы
$('#popUpForm').submit(function (event) {
    event.preventDefault();
    let date = [
        $('#year').val(),
        ($('#month').val() < 10 ? '0' : '') + $('#month').val() ,
        ($('#day').val() < 10 ? '0' : '') + $('#day').val() ].join('-');
    let employee = {
        name: $('#name').val(),
        lastName: $('#lastName').val(),
        positionId: +$('#position').val(),
        pass: ($('#pass').val()),
        dateOfEmployment: new Date(date).getTime(),
        id: id
    };

    $.ajax({
        url: url,
        data: employee,
        type: 'POST'
    })
        .done(function (data) {
            if (url === '/employees/add') {
                addEmployeeToTable(data, employee);
                employees[data] = employee;

            } else {
                tr = $('tr#' + id + '');
                tr.children()[0].innerText = employee.pass;
                tr.children()[1].innerText = employee.name;
                tr.children()[2].innerText = employee.lastName;
                tr.children()[3].innerText = positions[employee.positionId];
                tr.children()[4].innerText = date;
            }
        })
        .fail(function () {
            alert('Что-то пошло не так');
        })
        .always(function () {
            popUpHide();
        });
});