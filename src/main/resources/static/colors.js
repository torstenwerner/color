(function () {
    var module = angular.module('Colors', []);

    module.service("HueList", function ($http, $location) {
        var inDebugger = $location.port() == 63342;
        var url = inDebugger ? "http://localhost:8080/angles" : "angles";

        return {
            fetch: () => $http.get(url).then(response => response.data)
        };
    });

    module.filter("color", function() {
        return hue => "hsl(" + hue + ", 100%, 50%)";
    });

    module.controller('SvgController', function (HueList) {
        var svg = this;

        svg.lineWidth = 5;

        svg.getY = index => (2 * index + 1) * svg.lineWidth;

        HueList.fetch().then((hueList) => {
            svg.hueList = hueList;
        });
    });
}());
