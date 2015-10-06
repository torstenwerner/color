(function () {
    var lineWidth = 5;

    function draw(context, hues) {
        for (var i = 0; i < hues.length; i++) {
            context.beginPath();
            var y = (2 * i + 1) * lineWidth;
            context.moveTo(30, y);
            context.lineTo(499, y);
            context.lineWidth = lineWidth;
            context.strokeStyle = 'hsl(' + hues[i] + ', 100%, 50%)';
            context.stroke();

            context.textAlign = 'right';
            context.textBaseline = 'middle';
            context.fillText(hues[i], 19, y);

            context.beginPath();
            context.moveTo(30, y + lineWidth);
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
                $element[0].height = data.length * 2 * lineWidth;
                draw(context, data);
            });
    });
}());
