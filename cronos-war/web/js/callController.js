function callController() {
    //alert("callController is called");
    var request = new XMLHttpRequest();
    request.open('GET', 'UIController', false);
    request.send();
    if (request.status !== 200) {
        // обработать ошибку
        alert(request.status + ': ' + request.statusText); // пример вывода: 404: Not Found
    } else {
        // вывести результат
        var teamlist = JSON.parse(request.responseText); // responseText -- текст ответа.
    }
}