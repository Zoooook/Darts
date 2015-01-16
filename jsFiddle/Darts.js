function calculate(){
    var R = [0, parseInt(document.getElementById("d1").value), parseInt(document.getElementById("d2").value), parseInt(document.getElementById("d3").value), parseInt(document.getElementById("d4").value), parseInt(document.getElementById("d5").value), parseInt(document.getElementById("d6").value), parseInt(document.getElementById("d7").value), -1];
    var rings = [parseInt(document.getElementById("n1").value), parseInt(document.getElementById("n2").value), parseInt(document.getElementById("n3").value), parseInt(document.getElementById("n4").value), parseInt(document.getElementById("n5").value), parseInt(document.getElementById("n6").value), parseInt(document.getElementById("n7").value), parseInt(document.getElementById("n8").value)];

    document.getElementById("sigma").innerHTML = "\u03C3 = " + Math.sqrt(simpleEM(rings,100,100,R)).toFixed(2);
}


function simpleEM(rings, sInit, numIter, R){
    var s = sInit;
    for(var i=0; i<numIter; i++)
        s = simpleStep(rings,s, R);
    return s;
}

function simpleStep(rings, s, R){
    var a = [];
    for(var i=0; i<R.length-1; i++)
        a.push(((R[i]*R[i]+2*s)*Math.exp(-R[i]*R[i]/(2*s)) - (R[i+1]==-1 ? 0 : (R[i+1]*R[i+1]+2*s)*Math.exp(-R[i+1]*R[i+1]/(2*s)))) / (Math.exp(-R[i]*R[i]/(2*s)) - (R[i+1]==-1 ? 0 : Math.exp(-R[i+1]*R[i+1]/(2*s)))));

    var e = 0;
    var n = 0;
    for(var i=0; i<rings.length; i++){
        if(!isNaN(a[i]))
            e += rings[i]*a[i];
        n += rings[i];
    }
    return e/(2*n);
}

function increment(id){
    document.getElementById(id).value = parseInt(document.getElementById(id).value)+1;
}
