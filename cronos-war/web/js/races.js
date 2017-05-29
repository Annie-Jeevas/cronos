var details = "";

function getDetail(id) {
    request.open('POST', 'RaceController', id); //типа вызов метода получения результатов
    request.send();
    if (request.status !== 200) {
        // обработать ошибку
        alert(request.status + ': ' + request.statusText); // пример вывода: 404: Not Found
    } else {
        // вывести результат
        details = JSON.parse(request.responseText); // responseText -- текст ответа.
    }
}

var details = {
    id: "details",
    view: "label",
    align: "center",
    datatype: "json",
    width: 400,
    hidden: false
};

var raceArchive = {
    id: "raceArchive",
    view: "list",
    type: {
        width: "auto",
        template: "#id#. #fio# (#teamID#)"
    },
    url: "http://localhost:8080/cronos-war/RaceController",
    datatype: "json",
    editable: true,
    on: {
        onItemClick: function (id) {
            getDetail(id); //функция получения результатов гонки по id
            $$('details').define("label", details); //пока просто вывод результатов в label
            $$('details').refresh();
        }
    }
};









