let trToRedact;

$(document).ready(function () {
    popUpHide();
    getPositions();
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
        $.each(data, function (key, value) {
            console.log(value);
            addEmployeeToTable(value);
        });


    });
}

//Добавление сотрудника в таблицу
function addEmployeeToTable(employee) {

    let tr = $('<tr></tr>').attr("id", employee.id);
    $('tbody').append(tr);
    tr.append("<td>" + employee.pass + "</td>" +
        "<td>" + employee.name + "</td>" +
        "<td>" + employee.lastName + "</td>" +
        "<td>" + employee.position + "</td>" +
        "<td>" + employee.dateOfEmployment + "</td>")
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

//Получить коллекцию позиций
function getPositions() {
    $.getJSON('/positions', function (data) {
        console.log(data);
        for (let i = 0; i < data.length; i++){
            addSelectValue(data[i]);
        }
    });

    function addSelectValue(position) {
        let option = $('<option></option>>')
            .attr('value', position.id).text(position.value);
        $('#position').append(option);
    }
}

/*Обработчики кнопок*/
//Кнопка добавления сотрудника
$(".addButton").click(function () {
    $('input').val(null);
    $('button.submit').attr('id', '/employees/add');
    console.log($('button.submit').attr('id'));
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

    console.log(typeof parseInt(tr.id));
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

    $('.submit').attr('id', '/employees/edit');
    trToRedact = event.target.parentElement.parentElement;
    let date = trToRedact.children[4].innerText.split('-');

    $('#pass').val(trToRedact.children[0].innerText);
    $('#name').val(trToRedact.children[1].innerText);
    $('#lastName').val(trToRedact.children[2].innerText);
    $('#position').val($('option:contains('+trToRedact.children[3].innerText+')').val());
    $('#year').val(date[0]);
    $('#month').val(date[1]);
    $('#day').val(date[2]);

    popUpShow();
});

//Кнопка отмены вызова формы
$('.escapeButton').click(function () {
    $('input').val(null);
    $('#position').val(0);
    $('.submit').attr('id', '');
    trToRedact = undefined;
    popUpHide();
});

//Заполнение формы данными "липового" сотрудника
$('#getMock').click(function () {
    $('#name').val('Липовый');
    $('#lastName').val("Сотрудник");
    $('#position').val(0);
    $('#pass').val("A000");
    $('#year').val(2017);
    $('#month').val(12);
    $('#day').val(1);
});

//Отправка формы
$('#popUpForm').submit(function (event) {
    event.preventDefault();

    let urlValue = $('.submit').attr('id');

    let date = [
        $('#year').val(),
        $('#month').val(),
        $('#day').val()].join('-');

    let employee = {
        name: $('#name').val(),
        lastName: $('#lastName').val(),
        positionId: +$('#position').val(),
        pass: ($('#pass').val()),
        dateOfEmployment: date,
    };
    if(trToRedact !== undefined){
        employee['id'] = trToRedact['id'];
    }

    console.log(employee);
    console.log(urlValue);

    $.ajax({
        url: urlValue,
        data: employee,
        type: 'POST'
    })
        .done(function (data) {
            employee['position']=$('option[value='+employee.positionId+']').text();

            if (urlValue === '/employees/add') {
                employee['id'] = data;
                $('.submit').attr('id', '');
                addEmployeeToTable(employee);

            } else {
                trToRedact.children[0].innerText = employee.pass;
                trToRedact.children[1].innerText = employee.name;
                trToRedact.children[2].innerText = employee.lastName;
                trToRedact.children[3].innerText = employee.position;
                trToRedact.children[4].innerText = date;
            }
        })
        .fail(function () {
            alert('Что-то пошло не так');
        })
        .always(function () {
            popUpHide();
        });
});