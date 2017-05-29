
/* global $$ */

function addSportsmen() {
    var list = $$('listSportsmen');
    var data = {
        name: $$('addSportsmenForm').getValues().name,
        birthdate: $$('addSportsmenForm').getValues().birthdate,
        accuracy: $$('addSportsmenForm').getValues().accuracy / 10,
        gender: $$('addSportsmenForm').getValues().gender,
        team: $$('addSportsmenForm').getValues().team
    };
    list.add(data);
}
;

function addTeam() {
    var list = $$('listTeam');
    var data = {
        teamName: $$('addTeamForm').getValues().name
    };
    list.add(data);
}
;

var sideWidth = document.documentElement.clientHeight / 20;

var listTeam = {
    id: "listTeam",
    view: "list",
    url: "http://localhost:8080/cronos-war/TeamController",
    datatype: "json",
    save: "connector->http://localhost:8080/cronos-war/TeamController"
};

var listSportsmen = {
    id: "listSportsmen",
    view: "grouplist",
    save: "connector->http://localhost:8080/cronos-war/SportsmenController",
    templateItem: " #name# (#accuracy#)",
    templateBack: "Назад",
    /**
     *  почему то не умеет обращаться к полям другого поля через точку..
     *  мне кажется надо не через точку, ибо этож json, там не объекты тебе даются..
     *  как то подругому парсить надо. 
     *  легче будет добавить поле teamName ?? и выводить его как в тестовой версии было...
     */
    //templateGroup: "#team&teamName#",
    scheme: {
        $group:function(obj){
            return obj.team.id;
        }
    },
    select: true,
    type: {height: 60},
    url: "http://localhost:8080/cronos-war/SportsmenController",
    datatype: "json"
};

var addTeamForm = {
    view: "form",
    id: "addTeamForm",
    elements: [
        {
            name: "name",
            view: "text",
            placeholder: "Введите имя",
            align: "left",
            width: 250
        },
        {
            view: 'button',
            value: "Добавить",
            type: "form",
            align: "left",
            click: function () {
                $$('addTeamForm').hide();
                addTeam();
                $$('addTeamForm').clear();
            },
            width: 250
        },
        {
            view: "button",
            value: "Отменить",
            type: "danger",
            width: 250,
            click: "$$('addTeamForm').hide(),$$('addTeamForm').clear()"
        }
    ],
    hidden: true
};

var addSportsmenForm = {
    view: "form",
    id: "addSportsmenForm",
    elements: [
        {
            name: "name",
            view: "text",
            placeholder: "Введите имя",
            align: "left",
            width: 250
        },
        {
            name: "birthdate",
            view: "datepicker",
            value: new Date(1990, 1, 1),
            placeholder: "Введите дату рождения",
            align: "left",
            width: 250
        },
        {
            name: "accuracy",
            view: "slider",
            value: 5, // порядок точности какой?
            min: 1,
            max: 10,
            title: "Точность: 0.5",
            align: "left",
            width: 250,
            on: {
                onChange: function () {
                    this.define("title", "Точность: " + this.getValue() / 10);
                    this.refresh();

                },
                onSliderDrag: function () {
                    this.define("title", "Точность: " + this.getValue() / 10);
                    this.refresh();
                }
            }
        },
        {
            name: "gender",
            view: "radio",
            label: "Пол:",
            value: true,
            options: [
                {id: true, value: "М"},
                {id: false, value: "Ж"}
            ],
            align: "left",
            width: 250
        },
        {
            name: "team",
            view: "richselect",
            options: teamlist, // Заменить на listTeam когда будет связь с контроллерами ? 
            placeholder: "Выберите команду",
            align: "left",
            width: 250
        },
        {
            view: 'button',
            value: "Добавить",
            type: "form",
            align: "left",
            click: function () {
                addSportsmen();
                $$('addSportsmenForm').hide();
                $$('addSportsmenForm').clear();
            },
            width: 250
        },
        {
            view: "button",
            value: "Отменить",
            type: "danger",
            width: 250,
            click: "$$('addSportsmenForm').hide(),$$('addSportsmenForm').clear()"
        }
    ],
    hidden: true
};

