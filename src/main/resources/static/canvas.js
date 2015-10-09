(function () {
    var module = angular.module('Colors', []);

    module.service("HueList", function ($http) {
        var hueList = [];

        $http.get('angles').
            success(function (data) {
                data.forEach(function (hue) {
                    hueList.push(hue);
                });
            });

        return hueList;
    });

    module.controller('Canvas', function ($scope, HueList) {
        $scope.lineWidth = 5;

        $scope.getY = function (index) {
            return (2 * index + 1) * $scope.lineWidth;
        };

        $scope.color = function (hue) {
            return "hsl(" + hue + ", 100%, 50%)";
        };

        $scope.hueList = HueList;
    });
}());
