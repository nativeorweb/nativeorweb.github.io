/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

var count = 0;
var maxCount = 6;
var sizeMatrix = 550;

var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    



}
};


transpose = function() {

document.getElementById('a').innerHTML = "Começou funcao";

    var A = [];
    var B = [];
   
	    

    var result = [];
     


    var matrix = [];
for(var i=0; i<sizeMatrix; i++) {
    A[i] = [];
    for(var j=0; j<sizeMatrix; j++) {
        A[i][j] = ((20 * j) + i);
    }
}
    
  
for(var i=0; i<sizeMatrix; i++) {
    B[i] = [];
    for(var j=0; j<sizeMatrix; j++) {
        B[i][j] = ((25 * j) + i);
    }
}
   
    var mA = A.length;
    var nA = A[0].length;
    var mB = B.length;
    var nB = B[0].length;
   

    document.getElementById('a').innerHTML = "comecou a matriz: " + count;

    for (var i = 0; i < mA; i++) {
        result[i] = [];
        for (var j = 0; j < nB; j++) {
            var sum = 0;
            for (var k = 0; k < nA; k++) {
                sum += A[i][k] * B[k][j];
            }
            result[i][j] = sum;
        }
    }

document.getElementById('a').innerHTML = "acabou a matriz: " + count;
   if(count < maxCount){
    count++;
    
    transpose();
   }

 document.getElementById('b').innerHTML = "TERMINOUUUUU 05.22"

    console.log(navigator.vibrate(1500)); 
    return result;
    
};


function troisFois(){


//run_factorize(1332061140839377881275519727319087664563488856868363921090395424176003152616860694181381143398958886259607293657001126184200768318416415606007833158939494315794328961487646727235838064540020547096394715096987075662967270767111833128464892735927736680676281574819109293384227032660487105717000617743694091166592360430209859676725860069486092083162245353787489347996458344474036872405942603186910010107030249504890805053796812313931063356431538211577486558808551432480653655410786671149204990680160299130601476673122202346746073152960215724110679148813387815550919294278374132793382003237507202781781016933560748035199284389762236567868983336572644092830076478031437341713748965371275907861617842781207938340308618406806132341761652317375596844412105643666482330760029638578300024864280034704974456560072577302079710967422894034833870079056341042313954950303306609775366758875654233649026454012791177182484068909800375740635567792618409877923903328804607686747508551182788215187315706825259409787659457);

//run_factorize(
//168132340078966991479234843040214163814008879131005050199982686052275192862579281812200842498176844243480333541539804441269656855795790246718536005661390200961918084704920716014351507531406253948235628729161481417569772046047249313751887076455262444135248318465774602119797030821262907700716028329774917209760128807430324977084247794037397579051560003510237512296214214982383281629297197445267713301690633987314268086933159403403240453840213576426105549310423978241810065948770795631639703034173006193345651
//);

//run_factorize(
//2357111317192329313741434753596167717379838997101103107109113127131137139149151157163167173179181191193197199211223227229233239241251257263269271277281283293307311313317331337347349353359367373379383389397401409419421431433439443449457461463467479487491499503509521523541547557563569571577587593599601607613617619631641643647653659661673677683691701709719 
//);


// 200 digitos  16723757283622817643970295135225931466881865832845074525739178380754083893112407473417185413179962453270394094733035226381336021473520304649746202785974125014912401303142399999999999999999999999999999


isPrime(
1357911131517193133353739515355575971737577799193959799111113115117119131133135137139151153155157159171173175177179191193195197199311313315317319331333335337339351353355357359371373375377379391393395397399511513515517519531533535537539551553555557559571573575577579591593595597599711713715717719731733735737739751753755757759771
);
document.getElementById('b').innerHTML = "TERMINOUUUUU 06.25"

// achou 4669382 primos em java

}

var SoEPgClass = (function () {
    function SoEPgClass() {
        this.bi = -1; // constructor resets the enumeration to start...
    }
    SoEPgClass.prototype.next = function () {
        if (this.bi < 1) {
            if (this.bi < 0) {
                this.bi++;
                this.lowi = 0; // other initialization done here...
                this.bpa = [];
                return 2;
            } else { // bi must be zero:
                var nxt = 3 + (this.lowi << 1) + 262144;
                this.buf = new Array();
                for (var i = 0; i < 4096; i++) // faster initialization:
                    this.buf.push(0);
                if (this.lowi <= 0) { // special culling for first page as no base primes yet:
                    for (var i = 0, p = 3, sqr = 9; sqr < nxt; i++, p += 2, sqr = p * p)
                        if ((this.buf[i >> 5] & (1 << (i & 31))) === 0)
                            for (var j = (sqr - 3) >> 1; j < 131072; j += p)
                                this.buf[j >> 5] |= 1 << (j & 31);
                } else { // after the first page:
                    if (!this.bpa.length) { // if this is the first page after the zero one:
                        this.bps = new SoEPgClass(); // initialize separate base primes stream:
                        this.bps.next(); // advance past the only even prime of two
                        this.bpa.push(this.bps.next()); // get the next prime (3 in this case)
                    }
                    // get enough base primes for the page range...
                    for (var p = this.bpa[this.bpa.length - 1], sqr = p * p; sqr < nxt;
                            p = this.bps.next(), this.bpa.push(p), sqr = p * p) ;
                    for (var i = 0; i < this.bpa.length; i++) {
                        var p = this.bpa[i];
                        var s = (p * p - 3) >> 1;
                        if (s >= this.lowi) // adjust start index based on page lower limit...
                            s -= this.lowi;
                        else {
                            var r = (this.lowi - s) % p;
                            s = (r != 0) ? p - r : 0;
                        }
                        for (var j = s; j < 131072; j += p)
                            this.buf[j >> 5] |= 1 << (j & 31);
                    }
                }
            }
        }
        while (this.bi < 131072 && this.buf[this.bi >> 5] & (1 << (this.bi & 31)))
            this.bi++; // find next marker still with prime status
        if (this.bi < 131072) // within buffer: output computed prime
            return 3 + ((this.lowi + this.bi++) << 1);
        else { // beyond buffer range: advance buffer
            this.bi = 0;
            this.lowi += 131072;
            return this.next(); // and recursively loop
        }
    };
    return SoEPgClass;
})();

var gen = new SoEPgClass(); 
for (var i = 1; i < 8000; i++, gen.next());
var prime = gen.next();

document.getElementById('b').innerHTML = prime;






// 200 digitos 135791113151719313335373951535557597173757779919395979911111311511711913113313513713915115315515715917117317517717919119319519719931131331531731933133333533733935135335535735937137337537737939139339539739951151351551751953153353553753955155355555755957157357557757959159359559759971171371571771973173373573773975175375575775977

// 220 digitos 4711930799906184953162487834760260422020574773409675520188634839616415335845034221205289256705544681972439104097777157991804380284218315038719444943990492579030720635990538452312528339864352999310398481791730017201031731
//257 digitos 25000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001
//287 digitos 754504884488355992613475443703197599305472667423685663716426820258038260238341189837002895867489655037509404560697913282037450724137506302619022107255986292722755242991070733926076121552306935170095264015735976922876540696445988233036660723472298434191295086941047447198115204429821

//357 digitos 718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059921817413596629043572900334295260595630738132328627943490763233829880753195251019011573834187930702154089149934884167509244761460668082264800168477411853742345442437107539077744992069551702761838606261331384583000752044933826560297606737113
//289 digitos 3333333333333333331111111111111113313333333333333133131111111111131331313333333331313313131111111313133131313333313131331313131113131313313131313131313133131313111313131331313133333131313313131111111313133131333333333131331311111111111313313333333333333133111111111111111333333333333333333

//304 digitos 1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000237

//313 digitos 3136666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666313

//window.addEventListener('load', eratosthenes(
//55000
//));
app.initialize();

