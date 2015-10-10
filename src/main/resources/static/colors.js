(function () {
    var module = angular.module('Colors', []);

    module.service("HueList", function ($http) {
        return {
            fetch: function () {
                return $http.get('angles').
                    then(function (response) {
                        return response.data;
                    });
            }
        };
    });

    module.controller('Canvas', function ($scope, HueList) {
        $scope.lineWidth = 5;

        $scope.getY = function (index) {
            return (2 * index + 1) * $scope.lineWidth;
        };

        $scope.color = function (hue) {
            return "hsl(" + hue + ", 100%, 50%)";
        };

        HueList.fetch().then(function(hueList) {
            $scope.hueList = hueList;
        });
    });
}());
