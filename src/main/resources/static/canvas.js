(function () {
    var lineWidth = 5;

    function draw(context, hues) {
        for (var i = 0; i < hues.length; i++) {
            context.beginPath();
            var y = (2 * i + 1) * lineWidth;
            context.moveTo(0, y);
            context.lineTo(499, y);
            context.lineWidth = lineWidth;
            context.strokeStyle = 'hsl(' + hues[i] + ', 100%, 50%)';
            context.stroke();

            context.beginPath();
            context.moveTo(0, y + lineWidth);
            context.lineTo(499, y + lineWidth);
            context.lineWidth = lineWidth;
            context.strokeStyle = '#ffffff';
            context.stroke();
        }
    }

    angular.module('Colors', []).controller('Canvas', function ($http, $element) {
        var context = $element[0].getContext('2d');
        $http.get('angles').
            success(function (data) {
                $element[0].setAttribute('height', (data.length * lineWidth).toString());
                draw(context, data);
            });
    });
}());
