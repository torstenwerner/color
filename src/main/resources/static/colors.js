angular.module('Colors', []).

    service("HueList", function ($http, $location) {
        var inDebugger = $location.port() == 63342;
        var url = inDebugger ? "http://localhost:8080/angles" : "angles";

        return {
            fetch: () => $http.get(url).then(response => response.data)
        };
    }).

    filter("color", function () {
        return hue => "hsl(" + hue + ", 100%, 50%)";
    }).

    controller('SvgController', function (HueList) {
        var svg = this;

        svg.lineWidth = 5;

        svg.getY = index => (2 * index + 1) * svg.lineWidth;

        HueList.fetch().then((hueList) => {
            svg.hueList = hueList;
        });
    }).

    directive('colorDiagram', function() {
        return {
            retrict: 'E',
            replace: true,
            controller: 'SvgController',
            controllerAs: 'svg',
            templateUrl: 'colordiagram.html'
        }
    }).

    directive('colorBar', function() {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                y: '@',
                width: '@',
                color: '@',
                label: '@',
                showlabel: '='
            },
            templateUrl: 'colorbar.html'
        }
    });
