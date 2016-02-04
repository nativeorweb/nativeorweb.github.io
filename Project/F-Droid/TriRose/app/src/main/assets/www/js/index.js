var angle;
var TO_RAD = 0.01745329251994329576923690768489;
var hue = 0;
var ctr = 0;
var angle = 0;
var px1 = 0;
var px2 = 0;
var py1 = 0;
var py2 = 0;
var buffer = [];
var tamanho = 2500;

function execute(refine,angleInterval,k,screenMinDimension,screenMaxDimension,ox,oy){

    for (var i = 0; i < refine; ++i){
        angle += (angleInterval * TO_RAD) / refine;

        var radius = parseFloat(Math.cos(k * angle));
        radius *= (Math.min(screenMinDimension, screenMaxDimension) / 2) * 0.85;
        var x = parseFloat(radius * Math.cos(angle));
        var y = parseFloat(radius * Math.sin(angle));

        px1 = px2;
        py1 = py2;

        px2 = parseInt(ox + x);
        py2 = parseInt(oy - y);

        if (px1 == 0 && py1 == 0){
            continue;
            i--;
        }

        hue += 0.0005;
        while (hue > 1)
        hue -= 1;
        var color = HSLToRGB(hue, 1, 0.75);

        buffer[ctr++] = color;
        buffer[ctr++] = px1;
        buffer[ctr++] = py1;
        buffer[ctr++] = px2;
        buffer[ctr++] = py2;
        //        canvas.drawLine(px1, py1, px2, py2, paint);
    }
}

function onDraw(realTime,refine,angleInterval,k,screenMinDimension,screenMaxDimension,ox,oy){

    if(realTime == false){
        for(var inx = 0; inx < tamanho; inx++){
            execute(refine,angleInterval,k,screenMinDimension,screenMaxDimension,ox,oy)
        }

    }else{
        ctr = 0;
        execute(refine,angleInterval,k,screenMinDimension,screenMaxDimension,ox,oy)
    }
    Android.showVal(buffer);
}

function HSLToRGB(h,s,l){
    var r, g, b = 0;

    if (s == 0)
    {
        r = 1;
        g = 1;
        b = 1;
    }
    else
    {
        var q = l < 0.5 ? l * (1 + s) : l + s - l * s;
        var p = 2 * l - q;
        r = hue2rgb(p, q, h + 1 / 3);
        g = hue2rgb(p, q, h);
        b = hue2rgb(p, q, h - 1 / 3);
    }

    var red = parseInt(r * 255);
    var green = parseInt(g * 255);
    var blue = parseInt(b * 255);

    var rgb = 0xFF000000 | (red << 16) | (green << 8) | (blue);

    return rgb;
}

function hue2rgb(p, q, t){
    if (t < 0)
        t += 1;
    if (t > 1)
        t -= 1;
    if (t < 1 / 6)
        return p + (q - p) * 6 * t;
    if (t < 1 / 2)
        return q;
    if (t < 2 / 3)
        return p + (q - p) * (2 / 3 - t) * 6;
    return p;
}
