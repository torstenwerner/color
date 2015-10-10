(function () {
    var module = angular.module('Colors', []);

    module.service("HueList", ($http) => ({
        fetch: () => $http.get('angles').then(response => response.data)
    }));

    module.controller('Canvas', ($scope, HueList) => {
        $scope.lineWidth = 5;

        $scope.getY = index => (2 * index + 1) * $scope.lineWidth;

        $scope.color = hue => "hsl(" + hue + ", 100%, 50%)";

        HueList.fetch().then((hueList) => {
            $scope.hueList = hueList;
        });
    });
}());
