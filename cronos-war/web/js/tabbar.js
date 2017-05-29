var teamlist = [{id: 1, icon: "icons/norway.png", teamName: "Norway"},                
                {id: 2, icon: "icons/france.png", teamName: "France"},
                {id: 3, icon: "icons/russia.png", teamName: "Russia"}];
//тут мы все равно будем получать ajax'ом, так что пусть пока будет так
var sideWidth = document.documentElement.clientHeight / 20;
var tabsNumber = 4;
            var tabbar = {
                view: "segmented",
                id: 'tabbar',
                value: 'listView',
                multiview: true,
                optionWidth: document.documentElement.clientWidth / tabsNumber,
                align: "center",
                padding: 5,
                selected:"currentRace",
                options: [
                    {value: 'Sportsmen', id: 'listSportsmen'},
                    {value: 'Current Race', id: 'currentRace'},
                    {value: 'Last races results', id: 'archiveRaces'},
                    {value: 'Next Races', id: 'nextRaces'}
                ]
                
            };
var multyview = {
                id: "mymulti",
                animate:false,
                cells: [
                    {
                        id: "listSportsmen",
                        view: "list",
                        template: "#id#. <img src=#icon# height=32></> Team: #teamName# </div>",
                        type: {
                            height: 60
                        },                        
                        data: teamlist
                    },
                    {
                        id: "currentRace",
                        select: true,
                        view: "list"

                    },
                    {id: "archiveRaces", template: "<p>Прошедшие гонки</p>", padding: 5},
                    {id:"nextRaces", template:"<p>Следующие гонки</p>"}
                ]};
