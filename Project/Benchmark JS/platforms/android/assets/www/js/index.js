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
//var maxCount = 6;
//var sizeMatrix = 550;

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


    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=//
      //-=-=-=-=-=- !!!!!!UTIL FUNCTIONS!!!!! START-=-=-=-=--=-=
       //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=//

function criarArray(tamanhoArray){
    var ages = [];
    for (i = 0; i < tamanhoArray; i++) {
        ages.push(Math.floor(Math.random() * (tamanhoArray)));
    }
    return ages;
}

function criarArrayString(tamanhoArray){
    var ages = [];
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    for (i = 0; i < tamanhoArray; i++) {
        ages.push(possible.charAt(Math.floor(Math.random() * possible.length)));
    }
    return ages;
}

function settingFile(selector){
    var fileInput = document.getElementById('fileInput');

    fileInput.addEventListener('change', function(e) {
        fh = fileInput.files[0];
        var textType = /text.*/;

        if (fh.type.match(textType)) {
            if(selector == "func1"){
                textProcesing1(fh);
            }
            else if(selector == "func2"){
                var analyse = analyze_func(fh);
                analyse();
            }
            else{
                var analyse = textLicense(fh);
                analyse();
            }
        } else {
        }
    });
}

function finalizar(){

        document.getElementById('b').innerHTML =  "IT'S OVER" ;

        //document.getElementById('b').innerHTML =  "Teste is over" ;
        navigator.vibrate(1500);

//       if (navigator.app) {
//          navigator.app.exitApp();
//      } else if (navigator.device) {
//          navigator.device.exitApp();
//      }

}


  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=//
  //-=-=-=-=-=- !!!!!!UTIL FUNCTIONS!!!!! ENDS-=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=//

     //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=//
    //-=-=-=-=-=- MATRIX MULTIPLICATION START-=-=-=-=--=-=
     //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//
//
//function Matrix(ary) {
//    this.mtx = ary
//    this.height = ary.length;
//    this.width = ary[0].length;
//}
//
//Matrix.prototype.toString = function() {
//    var s = []
//    for (var i = 0; i < this.mtx.length; i++)
//        s.push( this.mtx[i].join(",") );
//    return s.join("\n");
//}
//
//Matrix.prototype.mult = function(other) {
//
//    if (this.width != other.height) {
//        throw "error: incompatible sizes";
//    }
//
//    var result = [];
//    for (var i = 0; i < this.height; i++) {
//        result[i] = [];
//        for (var j = 0; j < other.width; j++) {
//            var sum = 0;
//            for (var k = 0; k < this.width; k++) {
//                sum += this.mtx[i][k] * other.mtx[k][j];
//            }
//            result[i][j] = sum;
//        }
//    }
//
//    return new Matrix(result);
//}
//
//function multiplyMatrix(sizeMatrix){
//
//    var A = []
//
//     var B = []
//
//     for(var i=0; i<sizeMatrix; i++) {
//        A[i] = [];
//        for(var j=0; j<sizeMatrix; j++) {
//            A[i][j] = ((20 * j) + i);
//        }
//     }
//     for(var i=0; i<sizeMatrix; i++) {
//         B[i] = [];
//         for(var j=0; j<sizeMatrix; j++) {
//             B[i][j] = ((25 * j) + i);
//        }
//     }
//
//    var a = new Matrix(A);
//    var b = new Matrix(B);
//
//     a.mult(b);
//
//}


    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//-=-=-=-=-=- MATRIX MULTIPLICATION ENDS-=-=-=-=--=-=
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    // -=-=-=-=- SIEVE START=-=-=-=-=-
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//var SoEPgClass = (function () {
//    function SoEPgClass() {
//        this.bi = -1; // constructor resets the enumeration to start...
//    }
//    SoEPgClass.prototype.next = function () {
//        if (this.bi < 1) {
//            if (this.bi < 0) {
//                this.bi++;
//                this.lowi = 0; // other initialization done here...
//                this.bpa = [];
//                return 2;
//            } else { // bi must be zero:
//                var nxt = 3 + (this.lowi << 1) + 262144;
//                this.buf = new Array();
//                for (var i = 0; i < 4096; i++) // faster initialization:
//                    this.buf.push(0);
//                if (this.lowi <= 0) { // special culling for first page as no base primes yet:
//                    for (var i = 0, p = 3, sqr = 9; sqr < nxt; i++, p += 2, sqr = p * p)
//                        if ((this.buf[i >> 5] & (1 << (i & 31))) === 0)
//                            for (var j = (sqr - 3) >> 1; j < 131072; j += p)
//                                this.buf[j >> 5] |= 1 << (j & 31);
//                } else { // after the first page:
//                    if (!this.bpa.length) { // if this is the first page after the zero one:
//                        this.bps = new SoEPgClass(); // initialize separate base primes stream:
//                        this.bps.next(); // advance past the only even prime of two
//                        this.bpa.push(this.bps.next()); // get the next prime (3 in this case)
//                    }
//                    // get enough base primes for the page range...
//                    for (var p = this.bpa[this.bpa.length - 1], sqr = p * p; sqr < nxt;
//                            p = this.bps.next(), this.bpa.push(p), sqr = p * p) ;
//                    for (var i = 0; i < this.bpa.length; i++) {
//                        var p = this.bpa[i];
//                        var s = (p * p - 3) >> 1;
//                        if (s >= this.lowi) // adjust start index based on page lower limit...
//                            s -= this.lowi;
//                        else {
//                            var r = (this.lowi - s) % p;
//                            s = (r != 0) ? p - r : 0;
//                        }
//                        for (var j = s; j < 131072; j += p)
//                            this.buf[j >> 5] |= 1 << (j & 31);
//                    }
//                }
//            }
//        }
//        while (this.bi < 131072 && this.buf[this.bi >> 5] & (1 << (this.bi & 31)))
//            this.bi++; // find next marker still with prime status
//        if (this.bi < 131072) // within buffer: output computed prime
//{
//            return 3 + ((this.lowi + this.bi++) << 1);
//
//}       else { // beyond buffer range: advance buffer
//            this.bi = 0;
//            this.lowi += 131072;
//            return this.next(); // and recursively loop
//        }
//    };
//    return SoEPgClass;
//})();
//
//function sieve(number){
//var gen = new SoEPgClass();
//for (var i = 1; i < number; i++, gen.next());
//var prime = gen.next();
//}
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    // -=-=-=-=- SIEVE ENDS=-=-=-=-=-
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- TOWER HANOI START-=-=-=-=--=-=
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//function moveJS(n, a, b, c) {
//  if (n > 0) {
//    moveJS(n-1, a, c, b);
//  //  console.log("Move disk from " + a + " to " + c);
//    moveJS(n-1, b, a, c);
//  }
//}
//
//function moveJAVA(n, from, to, via) {
//
//  if (n == 1) {
//          //  System.out.println("Move disk from pole " + from + " to pole " + to);
//        } else {
//            moveJAVA(n - 1, from, via, to);
//            moveJAVA(1, from, to, via);
//            moveJAVA(n - 1, via, to, from);
//        }
//}


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- TOWER HANOI ENDS-=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- HAPPY NUMBERS START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//function happy(number) {
//    var m, digit ;
//    var cycle = [] ;
//
//    while(number != 1 && cycle[number] !== true) {
//        cycle[number] = true ;
//        m = 0 ;
//        while (number > 0) {
//            digit = number % 10 ;
//            m += digit * digit ;
//            number = (number  - digit) / 10 ;
//        }
//        number = m ;
//    }
//    return (number == 1) ;
//}
//
//function happyNumber(numMax){
//
//    var cnt = numMax ;
//    var number = 1 ;
//
//    while(cnt-- > 0) {
//        while(!happy(number))
//            number++ ;
//      //  document.write(number + " ") ;
//      number++;
//
//    }
//}
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- HAPPY NUMBERS ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- COMBINATIONS START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//function bitprint(u) {
//  var s="";
//  for (var n=0; u; ++n, u>>=1)
//    if (u&1) s+=n+" ";
//  return s;
//}
//function bitcount(u) {
// for (var n=0; u > 0; ++n, u=u&(u-1)); // modificação para o algoritmo ficar igual ao de java
//// for (var n=0; u; ++n, u=u&(u-1));
//  return n;
//}
//function comb(c,n) {
//  var s=[];
//  for (var u=0; u<1<<n; u++)
//    if (bitcount(u)==c)
//      s.push(bitprint(u))
//  return s.sort();
//}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- COMBINATIONS ENDS -=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=- COUNTFACTOR START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//function countfactor(number){
//
//    for(i = 1; i <= number; i++)
//        factor(i).join(" x ");
//
//}
//
//function factor(n) {
//    var factors = [];
//    if (n == 1) return [1];
//    for(p = 2; p <= n; ) {
//  if((n % p) == 0) {
//      factors[factors.length] = p;
//      n /= p;
//  }
//  else p++;
//    }
//    return factors;
//  }


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- COUNTFACTOR ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//-=-=-=-=-=- hofstadterQ START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


//var args;
//
//var hofstadterQ = function() {
//   var memo = [1,1,1];
//   var Q    = function (n) {
//      var result = memo[n];
//      if (typeof result !== 'number') {
//         result  = Q(n - Q(n-1)) + Q(n - Q(n-2));
//         memo[n] = result;
//      }
//      return result;
//   };
//   return Q;
//}();
//
//function executeHOFQ(number,loop){
//    for(var count = 0; count < loop; count++){
//        for (var i = 1; i <=number; i += 1) {
//            if(i == number-1){
//                document.getElementById('a').innerHTML =  hofstadterQ(number) ;
//            }else{
//                hofstadterQ(i);
//            }
//        }
//    }
//}


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- hofstadterQ ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//-=-=-=-=-=- hofstadterQ2 START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//var memo = [1,1,1];
//
//
// function Q {
//      var result = memo[n];
//      if (typeof result !== 'number') {
//         result  = Q(n - Q(n-1)) + Q(n - Q(n-2));
//         memo[n] = result;
//      }
//      return result;
//   };
//
//function init(){
//
//    var memo = [1,1,1];
//
//}
//
//function executeHOFQ2(number,loop){
//    for(var count = 0; count < loop; count++){
//        init();
//        for (var i = 1; i <=number; i += 1) {
//               Q(i);
//        }
//    }
//  }
//
//



 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- hofstadterQ2 ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


   //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- KnapsackUnbounded START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
/*
function knapsackunbounded() {

    var gold = { 'value': 2500, 'weight': 2.0, 'volume': 0.002 },
        panacea = { 'value': 3000, 'weight': 0.3, 'volume': 0.025 },
        ichor = { 'value': 1800, 'weight': 0.2, 'volume': 0.015 },
        silver = { 'value': 3500, 'weight': 0.7, 'volume': 0.022 },
        bronze  = { 'value': 500, 'weight': 0.9, 'volume': 0.014 },
        lump = { 'value': 5500, 'weight': 1.5, 'volume': 0.058 },
        helio = { 'value': 1500, 'weight': 1.7, 'volume': 0.032 },
        tulip = { 'value': 2500, 'weight': 0.7, 'volume': 0.062 },

        items = [gold, panacea, ichor,silver,bronze,lump,helio,tulip],
        knapsack = {'weight': 27, 'volume': 0.27},
        max_val = 0,
        solutions = [],
        g, p, i,s,b,l,h,t,
         item, val;

    for (i = 0; i < items.length; i += 1) {
        item = items[i];
        item.max = Math.min(
            Math.floor(knapsack.weight / item.weight),
            Math.floor(knapsack.volume / item.volume)
        );
    }

    for (g = 0; g <= gold.max; g += 1) {
        for (p = 0; p <= panacea.max; p += 1) {
             for (i = 0; i <= ichor.max; i += 1) {
               for (s = 0; s <= silver.max; s += 1) {
                    for (b = 0; b <= bronze.max; b += 1) {
                       for (l = 0; l <= lump.max; l += 1) {
                           for (h = 0; h <= helio.max; h += 1) {
                                for (t = 0; t <= tulip.max; t += 1) {
                                    if (i * ichor.weight + g * gold.weight + p * panacea.weight + s * silver.weight
                                    + b* bronze.weight+ l * lump.weight + h* helio.weight + t* tulip.weight
                                    > knapsack.weight) {
                                        continue;
                                    }
                                    if (i * ichor.volume + g * gold.volume + p * panacea.volume  + s * silver.volume
                                    + b* bronze.volume + l * lump.volume + h* helio.volume + t* tulip.volume
                                    > knapsack.volume) {
                                        continue;
                                    }
                                    val = i * ichor.value + g * gold.value + p * panacea.value + s * silver.value
                                    + b* bronze.value  + l * lump.value + h* helio.value + t* tulip.value
                                     ;
                                    if (val > max_val) {
                                        solutions = [];
                                        max_val = val;
                                    }
                                    if (val === max_val) {
                                        solutions.push([g, p, i, s, b ,l, h,t]);
                                    }
                                }
                           }
                       }
                    }
                }
            }
        }

    }

    
*//* document.write("maximum value: " + max_val + '<br>');
    for (i = 0; i < solutions.length; i += 1) {
        item = solutions[i];
         document.write("(gold: " + item[0] + ", panacea: " + item[1] + ", ichor: "
         + item[2] + ", silver: " + item[3] +", bronze: " + item[4] +  ", lump: "
         + item[5] +  ", helio: " + item[6] + ", tulip: " + item[7] +
         ")<br>");
    }*//*
}

 */
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- KnapsackUnbounded ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- ZeroOneKnapsack START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

/*
 * 0-1 knapsack solution, recursive, memoized, approximate.
 *
 * credits:
 *
 * the Go implementation here:
 *   http://rosettacode.org/mw/index.php?title=Knapsack_problem/0-1
 *
 * approximation details here:
 *   http://math.mit.edu/~goemans/18434S06/knapsack-katherine.pdf

knapsack = {};
(function() {
  this.combiner = function(items, weightfn, valuefn) {
    // approximation guarantees result >= (1-e) * optimal
    var _epsilon = 0.01;
    var _p = _.max(_.map(items,valuefn));
    var _k = _epsilon * _p / items.length;

    var _memo = (function(){
      var _mem = {};
      var _key = function(i, w) {
        return i + '::' + w;
      };
      return {
        get: function(i, w) {
          return _mem[_key(i,w)];
        },
        put: function(i, w, r) {
          _mem[_key(i,w)]=r;
          return r;
        }
      };
    })();

    var _m = function(i, w) {

      i = Math.round(i);
      w = Math.round(w);


      if (i < 0 || w === 0) {
        // empty base case
        return {items: [], totalWeight: 0, totalValue: 0};
      }

      var mm = _memo.get(i,w);
      if (!_.isUndefined(mm)) {
        return mm;
      }

      var item = items[i];
      if (weightfn(item) > w) {
        //item does not fit, try the next item
        return _memo.put(i, w, _m(i-1, w));
      }
      // this item could fit.
      // are we better off excluding it?
      var excluded = _m(i-1, w);
      // or including it?
      var included = _m(i-1, w - weightfn(item));
      if (included.totalValue + Math.floor(valuefn(item)/_k) > excluded.totalValue) {
        // better off including it
        // make a copy of the list
        var i1 = included.items.slice();
        i1.push(item);
        return _memo.put(i, w,
          {items: i1,
           totalWeight: included.totalWeight + weightfn(item),
           totalValue: included.totalValue + Math.floor(valuefn(item)/_k)});
      }
      //better off excluding it
      return _memo.put(i,w, excluded);
    };
    return {
      *//* one point *//*
      one: function(maxweight) {
        var scaled = _m(items.length - 1, maxweight);
        return {
          items: scaled.items,
          totalWeight: scaled.totalWeight,
          totalValue: scaled.totalValue * _k
        };
      },
      *//* the entire EF *//*
      ef: function(maxweight, step) {
        return _.map(_.range(0, maxweight+1, step), function(weight) {
          var scaled = _m(items.length - 1, weight);
          return {
            items: scaled.items,
            totalWeight: scaled.totalWeight,
            totalValue: scaled.totalValue * _k
          };
        });
      }
    };
  };
}).apply(knapsack);

var allwants = [

  {name:"map", weight:900, value: 150},
  {name:"compass", weight:1300, value: 35},
  {name:"water", weight:15300, value: 200},
  {name:"sandwich", weight: 5000, value: 160},
  {name:"glucose", weight:1500, value: 60},
  {name:"tin", weight:6800, value: 45},
  {name:"banana", weight:2700, value: 60},
  {name:"apple", weight:3900, value: 40},
  {name:"cheese", weight:2300, value: 30},
  {name:"beer", weight:5200, value: 10},
  {name:"suntan cream", weight:1100, value: 70},
  {name:"camera", weight:3200, value: 30},
  {name:"T-shirt", weight:2400, value: 15},
  {name:"trousers", weight:4800, value: 10},
  {name:"umbrella", weight:7300, value: 40},
  {name:"waterproof trousers", weight:4200, value: 70},
  {name:"waterproof overclothes", weight:4300, value: 75},
  {name:"note-case", weight:2200, value: 80},
  {name:"sunglasses", weight:700, value: 20},
  {name:"towel", weight:1800, value: 12},
  {name:"socks", weight:400, value: 50},
  {name:"book", weight:3000, value: 10},

  {name:"suntan cream", weight:1600, value: 35},
  {name:"camera", weight:5600, value: 70},
  {name:"T-shirt", weight:1500, value: 89},
  {name:"trousers", weight:25000, value: 78},
  {name:"umbrella", weight:2500, value: 18},
  {name:"waterproof trousers", weight:8900, value: 15},
  {name:"waterproof overclothes", weight:20000, value: 70},
  {name:"note-case", weight:1500, value: 13},
  {name:"sunglasses", weight:900, value: 22},
  {name:"towel", weight:8000, value: 42},
  {name:"socks", weight:800, value: 12},
  {name:"book", weight:3500, value: 200},
    {name:"suntan cream", weight:860, value: 89},
    {name:"camera", weight:760, value: 70},
    {name:"T-shirt", weight:2500, value: 89},
    {name:"trousers", weight:9800, value: 78},
    {name:"umbrella", weight:500, value: 18},
    {name:"waterproof trousers", weight:7600, value: 15},
    {name:"waterproof overclothes", weight:14000, value: 70},
    {name:"note-case", weight:6500, value: 13},
    {name:"sunglasses", weight:1400, value: 22},
    {name:"towel", weight:6500, value: 42},
    {name:"socks", weight:399200, value: 12},
    {name:"book", weight:5600, value: 200}


];

var near = function(actual, expected, tolerance) {
  if (expected === 0 && actual === 0) return true;
  if (expected === 0) {
    return Math.abs(expected - actual) / actual < tolerance;
  }
  return Math.abs(expected - actual) / expected < tolerance;
};

function zerooneknapsack(loop, number) {
    for(var i = 0; i < loop ; i++){
        var combiner =
            knapsack.combiner(allwants,
            function(x){return x.weight;},
            function(x){return x.value;});
        var oneport = combiner.one(number);
     }
   };
/*
test("frontier", function() {
  var combiner =
    knapsack.combiner(allwants,
      function(x){return x.weight;},
      function(x){return x.value;});
  var ef = combiner.ef(400, 1);
  equal(ef.length, 401, "401 because it includes the endpoints");
  ef = combiner.ef(400, 40);
  equal(ef.length, 11, "11 because it includes the endpoints");
  var expectedTotalValue = [
    0,
    330,
    445,
    590,
    685,
    755,
    810,
    860,
    902,
    960,
    1030
  ] ;
  _.each(ef, function(element, index) {
    // 15% error!  bleah!
    ok(near(element.totalValue, expectedTotalValue[index], 0.15),
      'actual ' + element.totalValue + ' expected ' + expectedTotalValue[index]);
  });
  deepEqual(_.pluck(ef, 'totalWeight'), [
    0,
    39,
    74,
    118,
    158,
    200,
    236,
    266,
    316,
    354,
    396
  ]);
  deepEqual(_.map(ef, function(x){return x.items.length;}), [
    0,
    4,
    6,
    7,
    9,
    10,
    10,
    12,
    14,
    11,
    12
   ]);
});*/

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- ZeroOneKnapsack ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- KnapsackBounded START -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
////
// function knapsackbounded(loop,size){
//
//  for(var i = 0; i < loop ; i++){
//    findBestPack(size)
//  }
//
//}
//
//var data= [
//{name: 'map',                    weight:  9, value:150, pieces:1},
//{name: 'compass',                weight: 13, value: 35, pieces:1},
//{name: 'water',                  weight:153, value:200, pieces:2},
//{name: 'sandwich',               weight: 50, value: 60, pieces:2},
//{name: 'glucose',                weight: 15, value: 60, pieces:2},
//{name: 'tin',                    weight: 68, value: 45, pieces:3},
//{name: 'banana',                 weight: 27, value: 60, pieces:3},
//{name: 'apple',                  weight: 39, value: 40, pieces:3},
//{name: 'cheese',                 weight: 23, value: 30, pieces:1},
//{name: 'beer',                   weight: 52, value: 10, pieces:3},
//{name: 'suntan, cream',          weight: 11, value: 70, pieces:1},
//{name: 'camera',                 weight: 32, value: 30, pieces:1},
//{name: 'T-shirt',                weight: 24, value: 15, pieces:2},
//{name: 'trousers',               weight: 48, value: 10, pieces:2},
//{name: 'umbrella',               weight: 73, value: 40, pieces:1},
//{name: 'waterproof, trousers',   weight: 42, value: 70, pieces:1},
//{name: 'waterproof, overclothes',weight: 43, value: 75, pieces:1},
//{name: 'note-case',              weight: 22, value: 80, pieces:1},
//{name: 'sunglasses',             weight:  7, value: 20, pieces:1},
//{name: 'towel',                  weight: 18, value: 12, pieces:2},
//{name: 'socks',                  weight:  4, value: 50, pieces:1},
//{name: 'book',                   weight: 30, value: 10, pieces:2},
//{name:"suntan cream", weight:1600, value: 35, pieces:3 },
//{name:"camera", weight:5600, value: 70, pieces:3 },
//{name:"T-shirt", weight:1500, value: 89, pieces:3 },
//{name:"trousers", weight:25000, value: 78, pieces:3 },
//{name:"umbrella", weight:2500, value: 18, pieces:3 },
//{name:"waterproof trousers", weight:8900, value: 15, pieces:3 },
//{name:"waterproof overclothes", weight:20000, value: 70, pieces:3 },
//{name:"note-case", weight:1500, value: 13, pieces:3 },
//{name:"sunglasses", weight:900, value: 22, pieces:3 },
//{name:"towel", weight:8000, value: 42, pieces:3 },
//{name:"socks", weight:800, value: 12, pieces:3 },
//{name:"book", weight:3500, value: 200, pieces:3 },
//{name:"suntan cream", weight:860, value: 89, pieces:3 },
//{name:"camera", weight:760, value: 70, pieces:3 },
//{name:"T-shirt", weight:2500, value: 89, pieces:3 },
//{name:"trousers", weight:9800, value: 78, pieces:3 },
//{name:"umbrella", weight:500, value: 18, pieces:3 },
//{name:"waterproof trousers", weight:7600, value: 15, pieces:3 },
//{name:"waterproof overclothes", weight:14000, value: 70, pieces:3 },
//{name:"note-case", weight:6500, value: 13, pieces:3 },
//{name:"sunglasses", weight:1400, value: 22, pieces:3 },
//{name:"towel", weight:6500, value: 42, pieces:3 },
//{name:"socks", weight:399200, value: 12, pieces:3 },
//{name:"book", weight:5600, value: 200, pieces:3 }
//];
//
//
//
//function findBestPack(size) {
//  var m= [[0]]; // maximum pack value found so far
//  var b= [[0]]; // best combination found so far
//  var opts= [0]; // item index for 0 of item 0
//  var P= [1]; // item encoding for 0 of item 0
//  var choose= 0;
//  for (var j= 0; j<data.length; j++) {
//    opts[j+1]= opts[j]+data[j].pieces; // item index for 0 of item j+1
//    P[j+1]= P[j]*(1+data[j].pieces); // item encoding for 0 of item j+1
//  }
//  for (var j= 0; j<opts[data.length]; j++) {
//    m[0][j+1]= b[0][j+1]= 0; // best values and combos for empty pack: nothing
//  }
//  for (var w=1; w<=size; w++) {
//    m[w]= [0];
//    b[w]= [0];
//    for (var j=0; j<data.length; j++) {
//      var N= data[j].pieces; // how many of these can we have?
//      var base= opts[j]; // what is the item index for 0 of these?
//      for (var n= 1; n<=N; n++) {
//        var W= n*data[j].weight; // how much do these items weigh?
//        var s= w>=W ?1 :0; // can we carry this many?
//        var v= s*n*data[j].value; // how much are they worth?
//        var I= base+n; // what is the item number for this many?
//        var wN= w-s*W; // how much other stuff can we be carrying?
//        var C= n*P[j] + b[wN][base]; // encoded combination
//        m[w][I]= Math.max(m[w][I-1], v+m[wN][base]); // best value
//        choose= b[w][I]= m[w][I]>m[w][I-1] ?C :b[w][I-1];
//      }
//    }
//  }
//  var best= [];
//  for (var j= data.length-1; j>=0; j--) {
//    best[j]= Math.floor(choose/P[j]);
//    choose-= best[j]*P[j];
//  }
//  //var out='<table><tr><td><b>Count</b></td><td><b>Item</b></td><th>unit weight</th><th>unit value</th>';
//  var wgt= 0;
//  var val= 0;
//  //for (var i= 0; i<best.length; i++) {
//  //  if (0==best[i]) continue;
//  //  out+='</tr><tr><td>'+best[i]+'</td><td>'+data[i].name+'</td><td>'+data[i].weight+'</td><td>'+data[i].value+'</td>'
//  //  wgt+= best[i]*data[i].weight;
//  //  val+= best[i]*data[i].value;
//  //}
////  out+= '</tr></table><br/>Total weight: '+wgt;
////  out+= '<br/>Total value: '+val;
////  document.body.innerHTML= out;


//}


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- KnapsackBounded ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//



 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- ManOrBoy STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//function a(k, x1, x2, x3, x4, x5) {
//  function b() {
//    k -= 1;
//    return a(k, b, x1, x2, x3, x4);
//  }
//  return (k > 0) ? b() : x4() + x5();
//}
//
//// this uses lambda wrappers around the numeric arguments
//function x(n) {
//  return function () {
//    return n;
//  };
//}
//
//function manorboy(loop,number){
//
//  for(var i = 0; i < loop ; i++){
//     a(number, x(1), x(-1), x(-1), x(1), x(0));
//       }
//
//}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- ManOrBoy ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- NQueens STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//function queenPuzzle(rows, columns) {
//    if (rows <= 0) {
//        return [[]];
//    } else {
//        return addQueen(rows - 1, columns);
//    }
//}
//
//function addQueen(newRow, columns, prevSolution) {
//    var newSolutions = [];
//    var prev = queenPuzzle(newRow, columns);
//    for (var i = 0; i < prev.length; i++) {
//        var solution = prev[i];
//        for (var newColumn = 0; newColumn < columns; newColumn++) {
//            if (!hasConflict(newRow, newColumn, solution))
//                newSolutions.push(solution.concat([newColumn]))
//        }
//    }
//    return newSolutions;
//}
//
//function hasConflict(newRow, newColumn, solution) {
//    for (var i = 0; i < newRow; i++) {
//        if (solution[i]     == newColumn          ||
//            solution[i] + i == newColumn + newRow ||
//            solution[i] - i == newColumn - newRow) {
//                return true;
//        }
//    }
//    return false;
//}
//
//function queenPuzzleExecute(loop,size){
//    for (var i = 0; i < loop; i++) {
//       queenPuzzle(size,size)
//    }
//}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- NQueens ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- NQueens2 STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

// var b = [];
// function unsafe(y){
//     var x = b[y];
//     for(var i = 1; i <= y; i++){
//        var t = b[y - i];
//        if (t == x ||
//              t == x - i ||
//              t == x + i) {
//          return true;
//        }
//     }
//     return false;
// }
// function putboard(number){
//
//    for (var y = 0; y < number; y++) {
//        for (var x = 0; x < number; x++) {
//          //  print((b[y] == x) ? "|Q" : "|_");
//        }
//     //   print("|");
//    }
// }
//
// function executeNQueens(loop,size){
//    for (var i = 0; i < loop; i++) {
//        run(size);
//    }
// }
// function run(number){
//    b = [];
//    var y = 0;
//    b[0] = -1;
//    while (y >= 0) {
//        do {
//            b[y]++;
//        } while ((b[y] < number) && unsafe(y));
//        if (b[y] < number) {
//            if (y < number-1) {
//             b[++y] = -1;
//            } else {
//                putboard(number);
//            }
//        } else {
//            y--;
//        }
//    }
// }



 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=- NQueens2 ENDS -=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- PerfectNumber STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//function is_perfect(n){
//
// var sum = 1, i, sqrt=Math.floor(Math.sqrt(n));
// for (i = sqrt-1; i>1; i--) {
//    if (n % i == 0) {
//        sum += i + n/i;
//    }
// }
// if(n % sqrt == 0){
//    sum += sqrt + (sqrt*sqrt == n ? 0 : n/sqrt);
// }
// return sum === n;
//}
//
//function perfectNumbers(number){
//    var i;
//    for (i = 0; i < number; i++){
//        if(is_perfect(i)){
//        }
//    }
//}
//
//perfectNumbers();

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- PerfectNumber ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- seqnonsquares STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

//function seqnonsquares(number){
//
//    for (var i = 1; i < number; i++) {
//        var args = i + Math.floor(1/2 + Math.sqrt(i));
//    }
//}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- seqnonsquares ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- bubblesort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
function bubblesort(ARRAY) {
  var done = false;
  while (! done) {
    done = true;
    for (var i = 1; i < ARRAY.length; i++) {
      if (ARRAY[i - 1] > ARRAY[i]) {
        done = false;
        var tmp = ARRAY[i - 1];
        ARRAY[i - 1] = ARRAY[i];
        ARRAY[i] = tmp;
      }
    }
  }
  return this;
}

function bubbleSortExecute(loop){

//    var baseList = ["Q","W","E","R","T","Y"];
//    var ARRAY = [];
//    var baseListSize = baseList.length;
//    for (var i = 0; i < loop*baseListSize; i++) {
//        ARRAY = baseList.push(baseList[i%baseListSize]);
//    }

    bubblesort(criarArrayString(loop));
}


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- bubblesort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- countSort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

var countSort = function(arr, min, max) {
    var i, z = 0, count = [];

    for (i = min; i <= max; i++) {
        count[i] = 0;
    }

    for (i=0; i < arr.length; i++) {
        count[arr[i]]++;
    }

    for (i = min; i <= max; i++) {
        while (count[i]-- > 0) {
            arr[z++] = i;
        }
    }

}

function countSortExecute(size){

    countSort(criarArray(size),0,size);

}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- countSort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- gnomeSort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

function gnomeSort(a) {
    function moveBack(i) {
        for( ; i > 0 && a[i-1] > a[i]; i--) {
            var t = a[i];
            a[i] = a[i-1];
            a[i-1] = t;
        }
    }
    for (var i = 1; i < a.length; i++) {
        if (a[i-1] > a[i]) moveBack(i);
    }
    return a;
}

function gnomeSortExecute(size){
       gnomeSort(criarArray(size));
}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- gnomeSort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- heap_sort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

function swap(data, i, j) {
    var tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
}

 function heap_sort(arr) {
    put_array_in_heap_order(arr);
    end = arr.length - 1;
    while(end > 0) {
        swap(arr, 0, end);
        sift_element_down_heap(arr, 0, end);
        end -= 1
    }
}

function put_array_in_heap_order(arr) {
    var i;
    i = arr.length / 2 - 1;
    i = Math.floor(i);
    while (i >= 0) {
        sift_element_down_heap(arr, i, arr.length);
        i -= 1;
    }
}

function sift_element_down_heap(heap, i, max) {
    var i_big, c1, c2;
    while(i < max) {
        i_big = i;
        c1 = 2*i + 1;
        c2 = c1 + 1;
        if (c1 < max && heap[c1] > heap[i_big])
            i_big = c1;
        if (c2 < max && heap[c2] > heap[i_big])
            i_big = c2;
        if (i_big == i) return;
        swap(heap,i, i_big);
        i = i_big;
    }
}



function heapSortExecute(size){
       heap_sort(criarArray(size));
}


//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- heap_sort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- insertionSort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

function insertionSort (a) {
    for (var i = 0; i < a.length; i++) {
        var k = a[i];
        for (var j = i; j > 0 && k < a[j - 1]; j--)
            a[j] = a[j - 1];
        a[j] = k;
    }
    return a;
}

function insertionSortExecute(size){
   insertionSort(criarArray(size));
}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- insertionSort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- merge_sort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
function merge(left, right, arr) {
  var a = 0;

  while (left.length && right.length) {
    arr[a++] = (right[0] < left[0]) ? right.shift() : left.shift();
  }
  while (left.length) {
    arr[a++] = left.shift();
  }
  while (right.length) {
    arr[a++] = right.shift();
  }
}

function mSort(arr, tmp, len) {
  if (len === 1) { return; }

  var m = Math.floor(len / 2),
      tmp_l = tmp.slice(0, m),
      tmp_r = tmp.slice(m);

  mSort(tmp_l, arr.slice(0, m), m);
  mSort(tmp_r, arr.slice(m), len - m);
  merge(tmp_l, tmp_r, arr);
}

function merge_sort(arr) {
  mSort(arr, arr.slice(), arr.length);
}


function mergeSortExecute(size,loop){
    for(var i = 0; i < loop ; i++){
        merge_sort(criarArray(size));
    }
}

//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- merge_sort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- pancake STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

Array.prototype.pancake_sort = function () {
    for (var i = this.length - 1; i >= 1; i--) {
        // find the index of the largest element not yet sorted
        var max_idx = 0;
        var max = this[0];
        for (var j = 1; j <= i; j++) {
            if (this[j] > max) {
                max = this[j];
                max_idx = j;
            }
        }

        if (max_idx == i)
            continue; // element already in place

        var new_slice;

        // flip this max element to index 0
        if (max_idx > 0) {
            new_slice = this.slice(0, max_idx+1).reverse();
            for (var j = 0; j <= max_idx; j++)
                this[j] = new_slice[j];
        }

        // then flip the max element to its place
        new_slice = this.slice(0, i+1).reverse();
        for (var j = 0; j <= i; j++)
            this[j] = new_slice[j];
    }
    return this;
}

function pancakeSortExecute(size){
   criarArray(size).concat().pancake_sort();
 }


//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- pancake ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- Quicksort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

Array.filter = function (collection) {
    return Array.prototype.filter.call(collection);
};

Array.prototype.quick_sort = function () {
    if (this.length < 2) {
        return this;
    }

    var pivot = this[Math.round(this.length / 2)];

    return this.filter(function (x) {
        return x < pivot;
    }).quick_sort().concat(this.filter(function (x) {
        return x == pivot;
    })).concat(this.filter(function (x) {
        return x > pivot;
    }).quick_sort());
};



function quickSortExecute(size){
    criarArray(size).quick_sort();
 }


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- Quicksort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- shellSort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


function shellSort (a) {
    for (var h = a.length; h = parseInt(h / 2);) {
        for (var i = h; i < a.length; i++) {
            var k = a[i];
            for (var j = i; j >= h && k < a[j - h]; j -= h)
                a[j] = a[j - h];
            a[j] = k;
        }
    }
    return a;
}

function shellSortExecute(size){
    shellSort(criarArray(size));

}

//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- shellSort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- SleepSort STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

////var a = 0;
////var times = 0;
////
////function reduzirA(){
////    a--;
////}
////
////Array.prototype.timeoutSort = function () {
////
////	this.forEach(function (n) {
////	    a++;
////	});
////
////	this.forEach(function (n) {
////	    setTimeout(reduzirA(), 50 * n)
////	});
////
////	while(a != 0) {
////	   // this.setTimeout(function(){},100);
////	}
////	times++;
////	document.getElementById('a').innerHTML =  times;
////}
//
//Array.prototype.timeoutSort = function (time) {
//	this.forEach(function (n) {
//		setTimeout(function () { }, time * n)
//	});
//}
//
//function timeoutSortExecute(size,loop,time){
//    var i;
//    for(i = 0; i < loop; i++){
//         criarArray(size).timeoutSort(time);
//    }
//}

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- SleepSort ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//




 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
  //-=-=-=-=-=- NOT EXECUTED -=-=-=-=--=-=
  //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- textProcesing1 STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


function textProcesing1(fh){

    var filename = 'readings.txt';
    var show_lines = 5;
    var file_stats = {
        'num_readings': 0,
        'total': 0,
        'reject_run': 0,
        'reject_run_max': 0,
        'reject_run_date': ''
    };

    var reader = new FileReader();
    reader.onload = function(progressEvent){

        for(var i = 0; i < 150 ; i++){
            var lines = this.result.split('\n');
            for(var line = 0; line < lines.length; line++){
                line_stats(lines[line], (show_lines-- > 0));
            }
        }
        report();
    };
    reader.readAsText(fh);

    function line_stats(line, print_line) {
        var readings = 0;
        var rejects = 0;
        var total = 0;
        var fields = line.split('\t');
        var date = fields.shift();

        while (fields.length > 0) {
            var value = parseFloat(fields.shift());
            var flag = parseInt(fields.shift(), 10);
            readings++;
            if (flag <= 0) {
                rejects++;
                file_stats.reject_run++;
            }
            else {
                total += value;
                if (file_stats.reject_run > file_stats.reject_run_max) {
                    file_stats.reject_run_max = file_stats.reject_run;
                    file_stats.reject_run_date = date;
                }
                file_stats.reject_run = 0;
            }
        }

        file_stats.num_readings += readings - rejects;
        file_stats.total += total;

    }

    // round a number to 3 decimal places
    function dec3(value) {
        return Math.round(value * 1e3) / 1e3;
    }

    function report(){

        document.getElementById('a').innerHTML =
            '\nFile(s)  = ' + filename + "\n" +
            "Total    = " + dec3(file_stats.total) + "\n" +
            "Readings = " + file_stats.num_readings + "\n" +
            "Average  = " + dec3(file_stats.total / file_stats.num_readings) + "\n\n" +
            "Maximum run of " + file_stats.reject_run_max +
            " consecutive false readings ends at " + file_stats.reject_run_date;

        finalizar();

    }
}

//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- textProcesing1 ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- DataMuning2 STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//// wrap up the counter variables in a closure.
//function analyze_func(fh) {
//    var dates_seen = {};
//    var format_bad = 0;
//    var records_all = 0;
//    var records_good = 0;
//    var dates = "";
//    return function() {
//       // while ( ! fh.atEndOfStream) {
//       //     var line = fh.ReadLine();
//            var reader = new FileReader();
//               reader.onload = function(progressEvent){
//                   var lines = this.result.split('\n');
//                   for(var line = 0; line < lines.length; line++){
//                        records_all ++;
//                        var allOK = true;
//                        var fields = lines[line].split('\t');
//                        if (fields.length != 49) {
//                           format_bad ++;
//                           continue;
//                        }
//
//                        var date = fields.shift();
//                        if (has_property(dates_seen, date))
//                          dates = dates + "\n" + date;
//                        else
//                           dates_seen[date] = 1;
//
//                        while (fields.length > 0) {
//                           var value = parseFloat(fields.shift());
//                           var flag = parseInt(fields.shift(), 10);
//                           if (isNaN(value) || isNaN(flag)) {
//                               format_bad ++;
//                           }
//                           else if (flag <= 0) {
//                               allOK = false;
//                           }
//                        }
//                        if (allOK)
//                           records_good ++;
//                   }
//                   document.getElementById('a').innerHTML = dates;
//                   report();
//               };
//               reader.readAsText(fh);
//
//       // }
//function report(){
//      finalizar();
//    document.getElementById('b').innerHTML =
//        "total records: " + records_all + "\n" +
//        "Wrong format: " + format_bad + "\n" +
//       "records with no bad readings: " + records_good
//}
//
//    }
//}
//
//
//function has_property(obj, propname) {
//    return typeof(obj[propname]) == "undefined" ? false : true;
//}

//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- DataMuning2 ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//

 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
    //-=-=-=-=-=- TextLicense STARTS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
//
//
//function textLicense(fh){
//
//    var in_use = 0, max_in_use = -1, max_in_use_at = [];
//
//    var reader = new FileReader();
//     reader.onload = function(progressEvent){
//         var lines = this.result.split('\n');
//         for(var line = 0; line < lines.length; line++){
//             if (lines[line].substr(8,3) == "OUT") {
//                    in_use++;
//                    if (in_use > max_in_use) {
//                        max_in_use = in_use;
//                        max_in_use_at = [ lines[line].split(' ')[3] ];
//                    }
//                    else if (in_use == max_in_use)
//                        max_in_use_at.push( lines[line].split(' ')[3] );
//                }
//                else if (lines[line].substr(8,2) == "IN")
//                    in_use--;
//         }
//         report();
//     };
//     reader.readAsText(fh);
//
//    function report(){
//          finalizar();
//        document.getElementById('b').innerHTML =
//            "Max licenses out: " +
//            max_in_use + "\n  " +
//            max_in_use_at.join('\n  ');
//    }
//}

//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//
 //-=-=-=-=-=- TextLicense ENDS -=-=-=-=--=-=
 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=//


execute();

 function execute(){







 //shellSortExecute(40000000);

  /*multiplyMatrix(650);
    sieve(80000000);
    moveJAVA(32, 1, 3, 2);
    moveJS(32, 1, 3, 2);
    happyNumber(500000);
    comb(75,30)
    countfactor(320000);
    executeHOFQ(2500000,250);
    knapsackunbounded();
    zerooneknapsack(10,180000) ;
    knapsackbounded(13,70000);
    manorboy(35000,13);
    queenPuzzleExecute(4,13);
    seqnonsquares(3500000000);
    executeHOFQ2(2500000,250);
    perfectNumbers(1500000)
    bubbleSortExecute(35000);
    countSortExecute(45000000)
    gnomeSortExecute(180000)
    heapSortExecute(25000000);
    insertionSortExecute(220000);
    mergeSortExecute(500000,5);
    pancakeSortExecute(50000);
    quickSortExecute(1800000);
    shellSortExecute(40000000);
  */
   /*





    knapsackbounded(13,70000);

//executeNQueens(4,13);



    heapSortExecute(25000000);
    insertionSortExecute(220000);
    mergeSortExecute(500000,5);



     timeoutSortExecute(450,10,10);

*/

finalizar();
//só pra saber
}

// function executeFile(){
//     settingFile("func1");
//        //settingFile("func2");
//        //settingFile("func3");
// }

//document.addEventListener('deviceready', execute, false);

//executeFile();
app.initialize();