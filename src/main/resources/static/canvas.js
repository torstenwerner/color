(function () {
    angular.module('Colors', []).controller('Canvas', function ($scope, $http) {
        $scope.lineWidth = 5;

        $scope.getY = function(index) {
            return (2 * index + 1) * $scope.lineWidth;
        };

        $scope.color = function(hue) {
            return "hsl(" + hue + ", 100%, 50%)";
        };

        $http.get('angles').
            success(function (data) {
                $scope.hueList = data;
            });
    });
}());
