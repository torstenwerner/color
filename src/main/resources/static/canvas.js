function draw(hues) {
    var canvas = document.getElementById('colors');
    var context = canvas.getContext('2d');
    var lineWidth = 5;
    canvas.setAttribute('height', 360 * lineWidth);

    for (var i = 0; i < 360; i++) {
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