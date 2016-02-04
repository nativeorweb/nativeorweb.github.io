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
    //  var listeningElement = parentElement.querySelector('.listening');
    //  var receivedElement = parentElement.querySelector('.received');

//      listeningElement.setAttribute('style', 'display:none;');
//      receivedElement.setAttribute('style', 'display:block;');
//
//      console.log('Received Event: ' + id);

    }
  };

//
//function loadFile(nameFile) {
//
//	//This alias is a read-only pointer to the app itself
//	window.resolveLocalFileSystemURL(cordova.file.applicationDirectory + "www/files/" + nameFile +".txt", gotFile, fail);
//
//	/* Yes, this works too for our specific example...
//	$.get("index.html", function(res) {
//		console.log("index.html", res);
//	});
//	*/
//
//}
//
function fail(e) {
	console.log("FileSystem Error");
	console.dir(e);
}

//function gotFile(fileEntry) {
//
//	fileEntry.file(function(file) {
//		var reader = new FileReader();
//
//		reader.onload = function(progressEvent){
//
//                for(var i = 0; i < 150 ; i++){
//                    var lines = this.result.split('\n');
//                    for(var line = 0; line < lines.length; line++){
//                        line_stats(lines[line], (show_lines-- > 0));
//                    }
//                }
//                report();
//            };
//            reader.readAsText(file);
//	});
//
//}


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- regex-dna STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//// The Computer Language Benchmarks Game
//// http://benchmarksgame.alioth.debian.org/

//// http://benchmarksgame.alioth.debian.org/u64q/program.php?test=regexdna&lang=v8&id=2

//// contributed by Jesse Millikan
//// Base on the Ruby version by jose fco. gonzalez
//// fixed by Matthew Wilson
//
//
////function executeRegen(fileName) {
////
////
////   var fileInput = document.getElementById('fileInput');
////
////   fileInput.addEventListener('change', function(e) {
////       fh = fileInput.files[0];
////        var textType = /text.*/;
////
////       runRegen(fh);
////   });
////
////
////}
//
//function executeRegen(nameFile) {
//
//	//This alias is a read-only pointer to the app itself
//	window.resolveLocalFileSystemURL(cordova.file.applicationDirectory + "www/files/" + nameFile +".txt", runRegen, fail);
//
//	/* Yes, this works too for our specific example...
//	$.get("index.html", function(res) {
//		console.log("index.html", res);
//	});
//	*/
//
//}
//
//function runRegen(fileEntry){
//
//     var i = "", ilen, clen, j, q = [ /agggtaaa|tttaccct/ig,
//      /[cgt]gggtaaa|tttaccc[acg]/ig, /a[act]ggtaaa|tttacc[agt]t/ig,
//      /ag[act]gtaaa|tttac[agt]ct/ig, /agg[act]taaa|ttta[agt]cct/ig,
//      /aggg[acg]aaa|ttt[cgt]ccct/ig, /agggt[cgt]aa|tt[acg]accct/ig,
//      /agggta[cgt]a|t[acg]taccct/ig, /agggtaa[cgt]|[acg]ttaccct/ig],
//      b = [ /B/g, '(c|g|t)', /D/g, '(a|g|t)', /H/g, '(a|c|t)', /K/g, '(g|t)',
//      /M/g, '(a|c)', /N/g, '(a|c|g|t)', /R/g, '(a|g)', /S/g, '(c|g)',
//      /V/g, '(a|c|g)', /W/g, '(a|t)', /Y/g, '(c|t)']
//
//	fileEntry.file(function(file) {
//		var reader = new FileReader();
//
//		reader.onloadend = function(progressEvent){
//
//                    var lines = this.result.split('\n');
//                    var lineIndex = 0;
//
//
//                    for(; lineIndex < lines.length; lineIndex++){
//                       i+=lines[lineIndex]+"\n";
//                       ilen = i.length
//                    }
//
//                    i = i.replace(/^>.*\n|\n/mg, ''); clen = i.length
//
//                    for(j = 0; j<q.length; ++j)
//                   // console.log(
//                   var args3 = q[j].source; var args = (i.match(q[j]) || []).length
//                  //  )
//
//                    for(j = -1; j<b.length - 1;) i = i.replace(b[++j], b[++j])
//                    //console.log(
//                    var args2 = ["", ilen, clen, i.length].join("\n")
//                    //)
//                    finalizar();
//            };
//            reader.readAsText(file);
//	});
//}

//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//     //-=-=-=-=-=- regex-dna ENDS -=-=-=-=--=-=
//     //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- spectral-norm STARTS -=-=-=-=--=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//// The Computer Language Benchmarks Game
  // http://benchmarksgame.alioth.debian.org/
  //http://benchmarksgame.alioth.debian.org/u64q/program.php?test=spectralnorm&lang=v8&id=2
  // contributed by Ian Osgood
  // modified by Isaac Gouy
//    function A(i,j) {
//      return 1/((i+j)*(i+j+1)/2+i+1);
//    }
//
//    function Au(u,v) {
//      for (var i=0; i<u.length; ++i) {
//        var t = 0;
//        for (var j=0; j<u.length; ++j)
//          t += A(i,j) * u[j];
//        v[i] = t;
//      }
//    }
//
//    function Atu(u,v) {
//      for (var i=0; i<u.length; ++i) {
//        var t = 0;
//        for (var j=0; j<u.length; ++j)
//          t += A(j,i) * u[j];
//        v[i] = t;
//      }
//    }
//
//    function AtAu(u,v,w) {
//      Au(u,w);
//      Atu(w,v);
//    }
//
//    function spectralnorm(n) {
//      var i, u=new Float64Array(n), v=new Float64Array(n), w=new Float64Array(n), vv=0, vBv=0;
//      for (i=0; i<n; ++i) {
//        u[i] = 1; v[i] = w[i] = 0;
//      }
//      for (i=0; i<10; ++i) {
//        AtAu(u,v,w);
//        AtAu(v,u,w);
//      }
//      for (i=0; i<n; ++i) {
//        vBv += u[i]*v[i];
//        vv  += v[i]*v[i];
//      }
//      return Math.sqrt(vBv/vv);
//    }
//
//    function executeSpectral(number){
//
//        spectralnorm(+number).toFixed(9)
//
//    }
//
//
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- spectral-norm ENDS -=-=-=-=--=-==-
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- fannkuch-redux STARTS -=-=-=-=--=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
/* The Computer Language Benchmarks Game
   http://benchmarksgame.alioth.debian.org/
http://benchmarksgame.alioth.debian.org/u64q/program.php?test=fannkuchredux&lang=v8&id=3
   contributed by Isaac Gouy, transliterated from Mike Pall's Lua program
   Modified by Roy Williams.
*/

//function fannkuch(n) {
//  var storage = new ArrayBuffer(12 * n);
//  var p = new Int32Array(storage, 0, n),
//      q = new Int32Array(storage, n * 4, n),
//      s = new Int32Array(storage, n * 8, n);
//  var sign = 1, maxflips = 0, sum = 0, m = n-1;
//
//  for(var i=0; i<n; i++){
//    p[i] = i;
//    q[i] = i;
//    s[i] = i;
//  }
//  do {
//    // Copy and flip.
//    var q0 = p[0];                                     // Cache 0th element.
//    if (q0 != 0){
//      for(var i=1; i<n; i++) {
//        q[i] = p[i];             // Work on a copy.
//      }
//      var flips = 1;
//      do {
//        var qq = q[q0];
//        if (qq == 0) {                            // ... until 0th element is 0.
//          sum = (sum + sign*flips) | 0;
//          if (flips > maxflips) {
//            maxflips = flips;   // New maximum?
//          }
//          break;
//        }
//        q[q0] = q0;
//        if (q0 >= 3) {
//          var i = 1,
//              j = (q0 - 1) | 0,
//              t;
//          do {
//            t = q[i];
//            q[i] = q[j];
//            q[j] = t;
//            i = (i + 1) | 0;
//            j = (j - 1) | 0;
//          } while (i < j);
//        }
//        q0 = qq;
//        flips = (flips + 1) | 0;
//      } while (true);
//    }
//    // Permute.
//    if (sign == 1) {
//      var t = p[1];
//      p[1] = p[0];
//      p[0] = t;
//      sign = -1; // Rotate 0<-1.
//    } else {
//      var t = p[1];
//      p[1] = p[2];
//      p[2] = t;
//      sign = 1;  // Rotate 0<-1 and 0<-1<-2.
//      for(var i=2; i<n; i++) {
//        var sx = s[i];
//        if (sx != 0) {
//          s[i] = (sx-1) | 0;
//          break;
//        }
//        if (i == m) {
//          return Array(sum,maxflips);      // Out of permutations.
//        }
//        s[i] = i;
//        // Rotate 0<-...<-i+1.
//        t = p[0];
//        for(var j=0; j<=i; j++) {
//          p[j] = p[j+1];
//        }
//        p[i+1] = t;
//      }
//    }
//  } while (true);
//}
//
//function executeFannkuch(number){
//
//    var n = +number;
//    var pf = fannkuch(n);
//    print(pf[0] + "\n" + "Pfannkuchen(" + n + ") = " + pf[1]);
//
//}

//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- fannkuch-redux ENDS -=-=-=-=--=-==-
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//
//
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- n-body STARTS -=-=-=-=--=-==-=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
///* The Computer Language Benchmarks Game
//   http://benchmarksgame.alioth.debian.org/
//http://benchmarksgame.alioth.debian.org/u64q/program.php?test=nbody&lang=v8&id=1

//   contributed by Isaac Gouy */
//
//var PI = 3.141592653589793;
//var SOLAR_MASS = 4 * PI * PI;
//var DAYS_PER_YEAR = 365.24;
//
//function Body(x,y,z,vx,vy,vz,mass){
//   this.x = x;
//   this.y = y;
//   this.z = z;
//   this.vx = vx;
//   this.vy = vy;
//   this.vz = vz;
//   this.mass = mass;
//}
//
//Body.prototype.offsetMomentum = function(px,py,pz) {
//   this.vx = -px / SOLAR_MASS;
//   this.vy = -py / SOLAR_MASS;
//   this.vz = -pz / SOLAR_MASS;
//   return this;
//}
//
//function Jupiter(){
//   return new Body(
//      4.84143144246472090e+00,
//      -1.16032004402742839e+00,
//      -1.03622044471123109e-01,
//      1.66007664274403694e-03 * DAYS_PER_YEAR,
//      7.69901118419740425e-03 * DAYS_PER_YEAR,
//      -6.90460016972063023e-05 * DAYS_PER_YEAR,
//      9.54791938424326609e-04 * SOLAR_MASS
//   );
//}
//
//function Saturn(){
//   return new Body(
//      8.34336671824457987e+00,
//      4.12479856412430479e+00,
//      -4.03523417114321381e-01,
//      -2.76742510726862411e-03 * DAYS_PER_YEAR,
//      4.99852801234917238e-03 * DAYS_PER_YEAR,
//      2.30417297573763929e-05 * DAYS_PER_YEAR,
//      2.85885980666130812e-04 * SOLAR_MASS
//   );
//}
//
//function Uranus(){
//   return new Body(
//      1.28943695621391310e+01,
//      -1.51111514016986312e+01,
//      -2.23307578892655734e-01,
//      2.96460137564761618e-03 * DAYS_PER_YEAR,
//      2.37847173959480950e-03 * DAYS_PER_YEAR,
//      -2.96589568540237556e-05 * DAYS_PER_YEAR,
//      4.36624404335156298e-05 * SOLAR_MASS
//   );
//}
//
//function Neptune(){
//   return new Body(
//      1.53796971148509165e+01,
//      -2.59193146099879641e+01,
//      1.79258772950371181e-01,
//      2.68067772490389322e-03 * DAYS_PER_YEAR,
//      1.62824170038242295e-03 * DAYS_PER_YEAR,
//      -9.51592254519715870e-05 * DAYS_PER_YEAR,
//      5.15138902046611451e-05 * SOLAR_MASS
//   );
//}
//
//function Sun(){
//   return new Body(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, SOLAR_MASS);
//}
//
//
//function NBodySystem(bodies){
//   this.bodies = bodies;
//   var px = 0.0;
//   var py = 0.0;
//   var pz = 0.0;
//   var size = this.bodies.length;
//   for (var i=0; i<size; i++){
//      var b = this.bodies[i];
//      var m = b.mass;
//      px += b.vx * m;
//      py += b.vy * m;
//      pz += b.vz * m;
//   }
//   this.bodies[0].offsetMomentum(px,py,pz);
//}
//
//NBodySystem.prototype.advance = function(dt){
//   var dx, dy, dz, distance, mag;
//   var size = this.bodies.length;
//
//   for (var i=0; i<size; i++) {
//      var bodyi = this.bodies[i];
//      for (var j=i+1; j<size; j++) {
//         var bodyj = this.bodies[j];
//         dx = bodyi.x - bodyj.x;
//         dy = bodyi.y - bodyj.y;
//         dz = bodyi.z - bodyj.z;
//
//         distance = Math.sqrt(dx*dx + dy*dy + dz*dz);
//         mag = dt / (distance * distance * distance);
//
//         bodyi.vx -= dx * bodyj.mass * mag;
//         bodyi.vy -= dy * bodyj.mass * mag;
//         bodyi.vz -= dz * bodyj.mass * mag;
//
//         bodyj.vx += dx * bodyi.mass * mag;
//         bodyj.vy += dy * bodyi.mass * mag;
//         bodyj.vz += dz * bodyi.mass * mag;
//      }
//   }
//
//   for (var i=0; i<size; i++) {
//      var body = this.bodies[i];
//      body.x += dt * body.vx;
//      body.y += dt * body.vy;
//      body.z += dt * body.vz;
//   }
//}
//
//NBodySystem.prototype.energy = function(){
//   var dx, dy, dz, distance;
//   var e = 0.0;
//   var size = this.bodies.length;
//
//   for (var i=0; i<size; i++) {
//      var bodyi = this.bodies[i];
//
//      e += 0.5 * bodyi.mass *
//         ( bodyi.vx * bodyi.vx
//         + bodyi.vy * bodyi.vy
//         + bodyi.vz * bodyi.vz );
//
//      for (var j=i+1; j<size; j++) {
//         var bodyj = this.bodies[j];
//         dx = bodyi.x - bodyj.x;
//         dy = bodyi.y - bodyj.y;
//         dz = bodyi.z - bodyj.z;
//
//         distance = Math.sqrt(dx*dx + dy*dy + dz*dz);
//         e -= (bodyi.mass * bodyj.mass) / distance;
//      }
//   }
//   return e;
//}
//
//function executeNbody(n){
//
//    var bodies = new NBodySystem( Array(
//       Sun(),Jupiter(),Saturn(),Uranus(),Neptune()
//    ));
//
//    bodies.energy().toFixed(9);
//    for (var i=0; i<n; i++){ bodies.advance(0.01); }
//    bodies.energy().toFixed(9);
//
//}
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- n-body ENDS -=-=-=-=--=-==-=-=-=
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- k-nucleotide STARTS -=-=-=-=--=-==-
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//
///*  The Computer Language Benchmarks Game
//    http://benchmarksgame.alioth.debian.org/
//http://benchmarksgame.alioth.debian.org/u64q/program.php?test=knucleotide&lang=v8&id=5
//    Contributed by Joe Farro
//    parts taken from solution contributed by
//    Jesse Millikan which was modified by Matt Baker
//*/
//
function executeKnucleotide(fileName) {

	//This alias is a read-only pointer to the app itself
	window.resolveLocalFileSystemURL(cordova.file.applicationDirectory + "www/files/" + fileName +".txt", runKnucleotide, fail);

	/* Yes, this works too for our specific example...
	$.get("index.html", function(res) {
		console.log("index.html", res);
	});
	*/

}

//function executeKnucleotide(selector){
//    var fileInput = document.getElementById('fileInput');
//
//    fileInput.addEventListener('change', function(e) {
//        fh = fileInput.files[0];
//        var textType = /text.*/;
//
//       runKnucleotide(fh);
//    });
//}


function SeqSets(len) {
    this.seqLen = len;
    this.uintLeft = 0;
    this.uintRight = 0;
    this.maskLeft = len <= 12 ? 0 : Math.pow(2, (len - 12) * 2) - 1;
    this.maskRight = Math.pow(2, Math.min(12, len) * 2) - 1;
    this.data = {};
    this.lastUintLeft = undefined;
    this.lastLeftData = undefined;
}

SeqSets.prototype.pushToken = function(char) {
    this.uintLeft = (this.uintLeft << 2 | this.uintRight >>> 22) & this.maskLeft;
    this.uintRight = (this.uintRight << 2 | char) & this.maskRight;
};

SeqSets.prototype.inc = function(char) {
    if (this.uintLeft !== this.lastUintLeft) {
        this.lastUintLeft = this.uintLeft;
        this.lastLeftData = this.data[this.uintLeft] = (this.data[this.uintLeft] || {});
    }
    this.lastLeftData[this.uintRight] = (this.lastLeftData[this.uintRight] || 0) + 1;
};

SeqSets.prototype.incWithToken = function(char) {
    this.pushToken(char);
    this.inc();
};

SeqSets.prototype.getCount = function(seq) {
    var seqLeft = seq.length <= 12 ? '' : seq.substr(0, seq.length - 12),
        seqRight = seq.substr(-12),
        uintLeft = seqLeft && toUint(seqLeft) || 0,
        uintRight = toUint(seqRight);

    return this.data[uintLeft][uintRight];
};


function charToInt(str) {
    switch (str) {
        case 'a': return 0;
        case 'c': return 1;
        case 'g': return 2;
        case 't': return 3;
    }
}

function toStr(num, len) {
    var res = '';
    while (len > 0) {
        switch (num & 3) {
            case 0: res = 'A' + res; break;
            case 1: res = 'C' + res; break;
            case 2: res = 'G' + res; break;
            case 3: res = 'T' + res; break;
        }
        num = num >>> 2;
        len--;
    }
    return res;
}

function toUint(str) {

    var offset = 2 * str.length,
        uint = new Uint32Array(new ArrayBuffer(4)),
        i = 0;

    while (offset) {
        offset -= 2;
        uint[0] |= (charToInt(str[i]) << offset);
        i++;
    }
    return uint[0];
}


var dataLength = 0;

var seq1 = new SeqSets(1),
    seq2 = new SeqSets(2),
    seq3 = new SeqSets(3),
    seq4 = new SeqSets(4),
    seq6 = new SeqSets(6),
    seq12 = new SeqSets(12),
    seq18 = new SeqSets(18);

var tables = [
    seq1,
    seq2,
    seq3,
    seq4,
    seq6,
    seq12,
    seq18,
];


function readInput(fileEntry) {

    var len = 0,
        line,
        i,
        char,
        si,
        slen = tables.length,
        seqSet;

    fileEntry.file(function(file) {
		var reader = new FileReader();

		reader.onload = function(progressEvent){

            var lines = this.result.split('\n');

            var lineIndex = 0;

            while((lines[lineIndex].substr(0, 3)) !== '>TH') {
                lineIndex++
            }

            lineIndex++;
            line = lines[lineIndex];
            i = 0;
            len = line.length;

            // the first-line is a special case as not all the counts should start
            // saving immediately
            while (i < 18) {

                char = charToInt(line[i]);

                si = 0;
                iPlusOne = i + 1;
                for (; si < slen; si++) {
                    seqSet = tables[si];
                    seqSet.pushToken(char);
                    if (seqSet.seqLen <= i + 1) {
                        seqSet.inc();
                    }
                }
                i++;
            }

            // use do-loop bc want to preserve `i` position on first line
            do {

                len = line.length;
                dataLength += len;
                while (i < len) {

                    char = charToInt(line[i]);

                    seq1.incWithToken(char);
                    seq2.incWithToken(char);
                    seq3.incWithToken(char);
                    seq4.incWithToken(char);
                    seq6.incWithToken(char);
                    seq12.incWithToken(char);
                    seq18.incWithToken(char);

                    i++;
                }
                i = 0;

                lineIndex++;
                line = lines[lineIndex];
            } while ((lineIndex < lines.length) && line[0] !== '>')

        endExecution();
        finalizar();
        };
        reader.readAsText(file);

	  });




}


function endExecution(){

    sortCounts(seq1.data[0], 1);
    sortCounts(seq2.data[0], 2);

   // console.log(seq3.getCount('ggt') +'\t' + 'GGT');
 //   console.log(seq4.getCount('ggta') +'\t' + 'GGTA');
 //   console.log(seq6.getCount('ggtatt') +'\t' + 'GGTATT');
  //  console.log(seq12.getCount('ggtattttaatt') +'\t' + 'GGTATTTTAATT');
  //  console.log(seq18.getCount('ggtattttaatttatagt') + '\t' + 'GGTATTTTAATTTATAGT');
}

function sortCounts(data, seqLen) {

    var keys = Object.keys(data),
        pctFactor = 100 / (dataLength - seqLen + 1);

    keys.sort(function(a, b) {
        return data[b] - data[a];
    });

    keys.forEach(function(code) {
    //    console.log(toStr(code, seqLen), (data[code] * pctFactor).toFixed(3));
    });
  //  console.log();
}


function runKnucleotide(fileEntry){

    readInput(fileEntry);



}

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- k-nucleotide ENDS -=-=-=-=--=-==-=-
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//
//     //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- reverse-complement STARTS -=-=-=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


/* The Computer Language Benchmarks Game
    http://benchmarksgame.alioth.debian.org/
http://benchmarksgame.alioth.debian.org/u64q/program.php?test=revcomp&lang=v8&id=4
    contributed by Joe Farro
    parts taken from solution contributed by
    Jos Hirth which was modified by 10iii
*/

var doTheLoop = 0;

var comp = [];
comp[65] = 'T';
comp[66] = 'V';
comp[67] = 'G';
comp[68] = 'H';
comp[71] = 'C';
comp[72] = 'D';
comp[75] = 'M';
comp[77] = 'K';
comp[78] = 'N';
comp[82] = 'Y';
comp[83] = 'S';
comp[84] = 'A';
comp[85] = 'A';
comp[86] = 'B';
comp[87] = 'W';
comp[89] = 'R';
comp[97] = 'T';
comp[98] = 'V';
comp[99] = 'G';
comp[100] = 'H';
comp[103] = 'C';
comp[104] = 'D';
comp[107] = 'M';
comp[109] = 'K';
comp[110] = 'N';
comp[114] = 'Y';
comp[115] = 'S';
comp[116] = 'A';
comp[117] = 'A';
comp[118] = 'B';
comp[119] = 'W';
comp[121] = 'R';

var LA_LEN = 995;

function LinkedArray(prev) {
    this.prev = prev;
    this.next = undefined;
    this.pos = 0;
    this.data = [];
}

function reverse(la) {

    var comps = comp,
        i,
        lines = la.data,
        lnIdx = la.pos - 1,
        line = lines[lnIdx],
        c = 1,
        buff = [''],
        buffIdx = 1,
        rev = new Array(61);

    rev[0] = '';

    for (; true; ) {

        for (i = line.length; i-- > 0; ++c) {
            rev[c] = comps[line.charCodeAt(i)];
            if (c === 60) {
                buff[buffIdx] = rev.join('');
                buffIdx++;
                c = 0;
            }
        }

        lnIdx--;
        line = lines[lnIdx];

        if (line !== undefined) {
            continue;
        }

        la = la.prev;
        if (la === undefined) {
            if (c > 1) {
                buff[buffIdx] = rev.join('').substr(0, c-1);
                buffIdx++;
            }
            buff[buffIdx] = '';
            buffIdx++;
            if (buffIdx < buff.length) {
              //  console.log(buff.slice(0,buffIdx).join('\n'));
            } else {
               // console.log(buff.join('\n'));
            }
            return;
        }

        lines = la.data;
        lnIdx = la.pos;
        lnIdx--;
        line = lines[lnIdx];
     //  console.log(buff.join('\n'));
        buffIdx = 1;
    }
}

function runRevComp(fileEntry){




    fileEntry.file(function(file) {
		var reader = new FileReader();

		reader.onload = function(progressEvent){


for(var loopinx = 0; loopinx < doTheLoop; loopinx++){

        var line,
        headLA = new LinkedArray(),
        la = headLA,
        lnIdx = 0,
        lines = la.data;

            var linesCTR = this.result.split('\n');
            var lineIndex = 0;

            line = linesCTR[lineIndex];

            //console.log(line);
            lineIndex++;

            for (; lineIndex < linesCTR.length ; lineIndex++) {
                line = linesCTR[lineIndex];
                if (line[0] !== '>') {

                    if (lnIdx === LA_LEN) {

                        la.pos = LA_LEN;

                        if (la.next === undefined) {
                            la = la.next = new LinkedArray(la);
                        } else {
                            la = la.next;
                        }
                        lines = la.data;
                        lines[0] = line;
                        lnIdx = la.pos = 1;
                    } else {
                        lines[lnIdx] = line;
                        lnIdx++;
                    }
                } else {
                    lc = 0;
                    la.pos = lnIdx;
                    reverse(la, comp);
                    //console.log(line);
                    la = headLA;
                    lines = la.data;
                    la.pos = 0;
                    lnIdx = 0;
                }
            }
            la.pos = lnIdx;
            reverse(la, comp);

        }
        finalizar();
        }
        reader.readAsText(file);
    });
}

function executeRevComp(loop,fileName) {

    doTheLoop = loop
	//This alias is a read-only pointer to the app itself
	window.resolveLocalFileSystemURL(cordova.file.applicationDirectory + "www/files/" + fileName +".txt", runRevComp, fail);

	/* Yes, this works too for our specific example...
	$.get("index.html", function(res) {
		console.log("index.html", res);
	});
	*/
}

////function executeRevComp(selector){
////    var fileInput = document.getElementById('fileInput');
////
////    fileInput.addEventListener('change', function(e) {
////        fh = fileInput.files[0];
////        var textType = /text.*/;
////
////       runRevComp(fh);
////    });
////}

//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- reverse-complement ENDS -=-=-=-=--
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//     //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- fasta STARTS -=-=-=-=--=-==-=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
// The Computer Language Benchmarks Game
  // http://benchmarksgame.alioth.debian.org/
  //http://benchmarksgame.alioth.debian.org/u64q/program.php?test=fasta&lang=v8&id=2
  //  Contributed by Ian Osgood
  //  Largely rewritten by Matthew Wilson
//
//function fastaRepeat(n, seq) {
//  var seqi = 0, len = seq.length, i, j, k, l, block,
//    str = Array(len*60+1).join(seq), lines = Array(i=j=len*len);
//  while (--j>-1) { lines[j] = str.substr(60*j, 60) }
//  block = lines.join("\n");
//  for (j=0, k=Math.floor((l=Math.floor(n/60))/i); j<k; ++j) {
//  //print(block)
//  }
//  for (j = 0, k = l % i; j < k; ++j) {
//   //print(lines[j])
//    }
//  if (n % 60 > 0) {
//  //print(lines[k].substr(0, n % 60))
//   }
//}
//
//var rand=(function() {
//  var Last = 42;
//  return function() { return (Last=(Last * 3877 + 29573) % 139968) / 139968 }
//})();
//
//function printLineMaker(table) {
//  var h = 0, k = [], v = [], c, l=0;
//  for (c in table) { l = v[h] = table[k[h++] = c]+=l; }
//  return function(x) {
//    var line = "";
//    next: for (var i=0; i<x; ++i) {
//      var r = rand(), j=0;
//      for (;;++j) {
//        if (r < v[j]) {
//          line += k[j];
//          continue next;
//        }
//      }
//    }
//    //print(line);
//  }
//}
//
//function fastaRandom(n, table) {
//  var printLine=printLineMaker(table);
//  while ((n -= 60) > -1) { printLine(60) }
//  if (n<0 && n>-60) { printLine(60 + n) }
//}
//
//function executeFasta(number){
//
//    (function main(n) {
//      var ALU = "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGG" +
//                "GAGGCCGAGGCGGGCGGATCACCTGAGGTCAGGAGTTCGAGA" +
//                "CCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAAT" +
//                "ACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCA" +
//                "GCTACTCGGGAGGCTGAGGCAGGAGAATCGCTTGAACCCGGG" +
//                "AGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACTGCACTCC" +
//                "AGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAA";
//
//      var IUB = { a:0.27, c:0.12, g:0.12, t:0.27, B:0.02, D:0.02, H:0.02, K:0.02,
//                  M:0.02, N:0.02, R:0.02, S:0.02, V:0.02, W:0.02, Y:0.02 }
//
//      var HomoSap = {
//        a:0.3029549426680, c:0.1979883004921, g:0.1975473066391, t:0.3015094502008
//      }
//
//      //print(">ONE Homo sapiens alu")
//      fastaRepeat(2*n, ALU)
//
//     // print(">TWO IUB ambiguity codes")
//      fastaRandom(3*n, IUB)
//
//     // print(">THREE Homo sapiens frequency")
//      fastaRandom(5*n, HomoSap)
//    }).call(this, 1*number*1)
//
//}
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- fasta ENDS -=-=-=-=--=-==-=-=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//
//     //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- binary-trees STARTS -=-=-=-=--=-==-=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
///* The Computer Language Benchmarks Game
//   http://benchmarksgame.alioth.debian.org/
// http://benchmarksgame.alioth.debian.org/u64q/program.php?test=binarytrees&lang=v8&id=1
//   contributed by Isaac Gouy */
//
//function TreeNode(left,right,item){
//   this.left = left;
//   this.right = right;
//   this.item = item;
//}
//
//TreeNode.prototype.itemCheck = function(){
//   if (this.left==null) return this.item;
//   else return this.item + this.left.itemCheck() - this.right.itemCheck();
//}
//
//function bottomUpTree(item,depth){
//   if (depth>0){
//      return new TreeNode(
//          bottomUpTree(2*item-1, depth-1)
//         ,bottomUpTree(2*item, depth-1)
//         ,item
//      );
//   }
//   else {
//      return new TreeNode(null,null,item);
//   }
//}
//
//function executeBinaryTree(number){
//    var minDepth = 4;
//    var n = number;
//    var maxDepth = Math.max(minDepth + 2, n);
//    var stretchDepth = maxDepth + 1;
//
//    var check = bottomUpTree(0,stretchDepth).itemCheck();
//    print("stretch tree of depth " + stretchDepth + "\t check: " + check);
//
//    var longLivedTree = bottomUpTree(0,maxDepth);
//    for (var depth=minDepth; depth<=maxDepth; depth+=2){
//       var iterations = 1 << (maxDepth - depth + minDepth);
//
//       check = 0;
//       for (var i=1; i<=iterations; i++){
//          check += bottomUpTree(i,depth).itemCheck();
//          check += bottomUpTree(-i,depth).itemCheck();
//       }
//       print(iterations*2 + "\t trees of depth " + depth + "\t check: " + check);
//    }
//
//    print("long lived tree of depth " + maxDepth + "\t check: "
//       + longLivedTree.itemCheck());
//}

//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//    //-=-=-=-=-=- binary-trees ENDS -=-=-=-=--=-==-=-=-=
//    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//

function execute(){
// COMPATIBLE

    //executeBinaryTree(19);
    //executeFannkuch(12);
    //executeFasta(50000000);
    //executeNbody(50000000);
    //executeSpectral(10000);



finalizar();
}
function executeFile(){
    executeRevComp(20,"Input2500000");
    //executeRegen("Input5000000");
    //executeKnucleotide("Input5000000");
}

document.addEventListener('deviceready', executeFile, false);
//executeFile();
app.initialize();



function finalizar(){

        document.getElementById('b').innerHTML =  "IT'S OVER" ;

        //document.getElementById('b').innerHTML =  "Teste is over" ;
        navigator.vibrate(1500);

}