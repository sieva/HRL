$(function () {
    var body = $('body');
    var backgrounds = new Array(
            'url(pictures/moto4.bmp)',
            'url(pictures/moto1.bmp)',
            'url(pictures/moto2.bmp)',
            'url(pictures/moto3.bmp)',
            'url(pictures/moto5.bmp)',
            'url(pictures/moto6.bmp)',
            'url(pictures/moto7.bmp)',
            'url(pictures/moto8.bmp)',
            'url(pictures/moto9.bmp)',
            'url(pictures/moto10.bmp)',
            'url(pictures/moto11.bmp)',
            'url(pictures/moto12.bmp)',
            'url(pictures/moto13.bmp)',
            'url(pictures/moto14.bmp)',
            'url(pictures/moto15.bmp)',
            'url(pictures/moto16.bmp)',
            'url(pictures/moto17.bmp)',
            'url(pictures/moto18.bmp)',
            'url(pictures/moto19.bmp)',
            'url(pictures/moto20.bmp)',
            'url(pictures/moto21.bmp)'
            );
    var current = 0;
    function nextBackground() {
        body.css('background',
                backgrounds[current = ++current % backgrounds.length]
                );
        setTimeout(nextBackground, 10000);
    }
    setTimeout(nextBackground, 10000);
    body.css('background', backgrounds[0]);
}
);



