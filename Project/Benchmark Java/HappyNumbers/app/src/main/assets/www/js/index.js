    function teste(){

        Android.teste("teste");
    }

    function seqnonsquares(number){

        for (var i = 1; i < number; i++) {
            var args = i + Math.floor(1/2 + Math.sqrt(i));
        }
        Android.showToast("acabou");
    }

    seqnonsquares(3500000000);