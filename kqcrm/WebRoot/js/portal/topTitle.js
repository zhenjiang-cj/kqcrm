var v = window,ma = Function,$ = navigator,u = Number,J = Error,R = document,la = Boolean,o = Math,S = isNaN,m = undefined;
function pa(a, b) {
    return a.height = b
}
function ra(a, b) {
    return a.relatedTarget = b
}
function V(a, b) {
    return a.currentTarget = b
}
function ea(a, b) {
    return a.toString = b
}
function qa(a, b) {
    return a.left = b
}
function W(a, b) {
    return a.width = b
}
var Oa = "document",L = "height",za = "slice",na = "offsetLeft",B = "relatedTarget",K = "currentTarget",ba = "offsetTop",da = "target",i = "length",ca = "play",w = "type",kb = "firstChild",_P = "prototype",ya = "nodeType",k = "style",aa = "left",D = "width",U = "parentNode",oa = "offsetWidth",xa = "body",lb = lb ||
                                                                                                                                                                                                                                                                                                                       {},I = this;
var l = function(a) {
    return typeof a != "undefined"
},O = function(a) {
    return a instanceof Array || va(a) && typeof a.join == "function" && typeof a.reverse == "function"
},wa = function(a) {
    return typeof a == "string"
},db = function(a) {
    return typeof a == "number"
},cb = function(a) {
    return typeof a == "function"
},va = function(a) {
    return a != null && typeof a == "object"
},A = function(a) {
    if (a.hasOwnProperty && a.hasOwnProperty(fa)) {
        return a[fa]
    }
    if (!a[fa]) {
        a[fa] = String(++Pb)
    }
    return a[fa]
},fa = "closure_hashCode_",Pb = 0,sb = function(a) {
    if (va(a)) {
        if (a.clone) {
            return a.clone()
        }
        var b = O(a) ? [] : {};
        for (var c in a) {
            b[c] = sb(a[c])
        }
        return b
    }
    return a
},rb = function(a, b) {
    var c = a.na || [];
    c = c.concat(Array[_P][za].call(arguments, 2));
    if (a.Q) {
        b = a.Q
    }
    if (a.P) {
        a = a.P
    }
    var d = function() {
        var f = c.concat(Array[_P][za].call(arguments));
        return a.apply(b, f)
    };
    d.na = c;
    d.Q = b;
    d.P = a;
    return d
},Qb = function(a, b) {
    for (var c in b) {
        a[c] = b[c]
    }
};
if (!ma[_P].apply) {
    ma[_P].apply = function(a, b) {
        var c = [],d,f;
        if (!a)a = I;
        if (!b)b = [];
        for (var e = 0; e < b[i]; e++) {
            c[e] = "args[" + e + "]"
        }
        f = "oScope.__applyTemp__.peek().(" + c.join(",") + ");";
        if (!a.__applyTemp__) {
            a.__applyTemp__ = []
        }
        a.__applyTemp__.push(this);
        d = eval(f);
        a.__applyTemp__.pop();
        return d
    }
}
ma[_P].bind = function(a) {
    return rb.apply(null, [this,a].concat(Array[_P][za].call(arguments, 1)))
};
ma[_P].inherits = function(a) {
    function b() {
    }

    b.prototype = a[_P];
    this.oa = a[_P];
    this.prototype = new b;
    this[_P].constructor = this
};
if (!Array[_P].push) {
    Array[_P].push = function() {
        for (var a = 0; a < arguments[i]; a++) {
            this[this[i]] = arguments[a]
        }
        return this[i]
    }
}
if (!Array[_P].pop) {
    Array[_P].pop = function() {
        var a;
        if (this[i]) {
            a = this[this[i] - 1];
            this.length--
        }
        return a
    }
}
Array[_P].peek = function() {
    return this[this[i] - 1]
};
if (!Array[_P].shift) {
    Array[_P].shift = function() {
        var a;
        if (this[i]) {
            a = this[0];
            for (var b = 0; b < this[i] - 1; b++) {
                this[b] = this[b + 1]
            }
            this.length--
        }
        return a
    }
}
if (!Array[_P].unshift) {
    Array[_P].unshift = function() {
        var a = arguments[i];
        for (var b = this[i] - 1; b >= 0; b--) {
            this[b + a] = this[b]
        }
        for (var c = 0; c < a; c++) {
            this[c] = arguments[c]
        }
        return this[i]
    }
}
;
var Pa = function(a, b, c) {
    if (a.indexOf) {
        return a.indexOf(b, c)
    }
    if (Array.indexOf) {
        return Array.indexOf(a, b, c)
    }
    if (c == null) {
        c = 0
    } else if (c < 0) {
        c = o.max(0, a[i] + c)
    }
    for (var d = c; d < a[i]; d++) {
        if (a[d] === b)return d
    }
    return-1
},nb = function(a, b, c) {
    if (a.filter) {
        return a.filter(b, c)
    }
    if (Array.filter) {
        return Array.filter(a, b, c)
    }
    var d = a[i],f = [],e = wa(a) ? a.split("") : a;
    for (var h = 0; h < d; h++) {
        if (b.call(c, e[h], h, a)) {
            f.push(e[h])
        }
    }
    return f
},ob = function(a, b, c) {
    if (a.map) {
        return a.map(b, c)
    }
    if (Array.map) {
        return Array.map(a, b, c)
    }
    var d = a[i],f = [],e = wa(a) ? a.split("") : a;
    for (var h = 0; h < d; h++) {
        f.push(b.call(c, e[h], h, a))
    }
    return f
},mb = function(a, b) {
    if (a.contains) {
        return a.contains(b)
    }
    return Pa(a, b) > -1
},pb = function(a, b) {
    var c = Pa(a, b),d;
    if (d = c != -1) {
        qb(a, c)
    }
    return d
},qb = function(a, b) {
    return Array[_P].splice.call(a, b, 1)[i] == 1
};
var Rb = function(a, b) {
    var c;
    if (c = b in a) {
        delete a[b]
    }
    return c
};
var Sb = function(a) {
    var b = {};
    for (var c = 0; c < a[i]; c++) {
        b[a.charAt(c)] = true
    }
    return b
},gc = Sb("()[]{}+-?*.$^|,:#<!\\");
var Z = function(a, b) {
    W(this, l(a) ? u(a) : m);
    pa(this, l(b) ? u(b) : m)
};
Z[_P].clone = function() {
    return new Z(this[D], this[L])
};
ea(Z[_P], function() {
    return"(" + this[D] + " x " + this[L] + ")"
});
Z.equals = function(a, b) {
    if (a == b) {
        return true
    }
    if (!a || !b) {
        return false
    }
    return a[D] == b[D] && a[L] == b[L]
};
var G = function(a, b) {
    this.x = l(a) ? u(a) : m;
    this.y = l(b) ? u(b) : m
};
G[_P].clone = function() {
    return new G(this.x, this.y)
};
ea(G[_P], function() {
    return"(" + this.x + ", " + this.y + ")"
});
G.equals = function(a, b) {
    if (a == b) {
        return true
    }
    if (!a || !b) {
        return false
    }
    return a.x == b.x && a.y == b.y
};
G.distance = function(a, b) {
    var c = a.x - b.x,d = a.y - b.y;
    return o.sqrt(c * c + d * d)
};
G.squaredDistance = function(a, b) {
    var c = a.x - b.x,d = a.y - b.y;
    return c * c + d * d
};
G.difference = function(a, b) {
    return new G(a.x - b.x, a.y - b.y)
};
var Ja = function(a, b) {
    a = u(a);
    b = u(b);
    this.start = a < b ? a : b;
    this.end = a < b ? b : a
};
Ja[_P].clone = function() {
    return new Ja(this.start, this.end)
};
ea(Ja[_P], function() {
    return"[" + this.start + ", " + this.end + "]"
});
var Ka = function(a, b, c, d) {
    qa(this, l(a) ? u(a) : m);
    this.top = l(b) ? u(b) : m;
    W(this, l(c) ? u(c) : m);
    pa(this, l(d) ? u(d) : m)
};
Ka[_P].clone = function() {
    return new Ka(this[aa], this.top, this[D], this[L])
};
ea(Ka[_P], function() {
    return"(" + this[aa] + ", " + this.top + " - " + this[D] + "w x " + this[L] + "h)"
});
var Ia = function(a, b, c, d) {
    this.top = l(a) ? u(a) : m;
    this.right = l(b) ? u(b) : m;
    this.bottom = l(c) ? u(c) : m;
    qa(this, l(d) ? u(d) : m)
};
Ia[_P].clone = function() {
    return new Ia(this.top, this.right, this.bottom, this[aa])
};
ea(Ia[_P], function() {
    return"(" + this.top + "t, " + this.right + "r, " + this.bottom + "b, " + this[aa] + "l)"
});
var Ma,ka,Xb,fb,Zb,gb,Yb,Na,bc,ac,cc,$b;
(function() {
    var a = $.userAgent,b = typeof opera != "undefined",c = !b && a.indexOf("MSIE") != -1,d = !b && a.indexOf("Safari") != -1,f = !b && $.product == "Gecko" &&
                                                                                                                                  !d,e = f && $.vendor ==
                                                                                                                                              "Camino",h = !b &&
                                                                                                                                                           a.indexOf("Konqueror") !=
                                                                                                                                                           -1,j = h ||
                                                                                                                                                                  d,n,p;
    if (b) {
        n = opera.version()
    } else {
        if (f) {
            p = /rv\:([^\);]+)(\)|;)/
        } else if (c) {
            p = /MSIE\s+([^\);]+)(\)|;)/
        } else if (d) {
            p = /AppleWebKit\/(\S+)/
        } else if (h) {
            p = /Konqueror\/([^\);]+)(\)|;)/
        }
        if (p) {
            p.test(a);
            n = RegExp.$1
        }
    }
    var T = $.platform,hb = T.indexOf("Mac") != -1,ib = T.indexOf("Win") != -1,jb = T.indexOf("Linux") != -1;
    Ma = b;
    ka = c;
    Xb = f;
    fb = e;
    Zb = h;
    gb = d;
    Yb = j;
    Na = n;
    bc = $.platform;
    ac = hb;
    cc = ib;
    $b = jb
})();
var dc = function(a, b) {
    if (!S(a) && !S(b)) {
        return a - b
    }
    var c = a.split("."),d = b.split("."),f = o.min(c[i], d[i]);
    for (var e = 0; e < f; e++) {
        if (typeof d[e] == "undefined") {
            return 1
        }
        if (typeof c[e] == "undefined") {
            return-1
        }
        if (!S(c[e]) && S(d[e]) && c[e] == parseInt(d[e], 10)) {
            return 1
        }
        if (S(c[e]) && !S(d[e]) && parseInt(c[e], 10) == d[e]) {
            return-1
        }
        if (d[e] > c[e]) {
            return-1
        } else if (d[e] < c[e]) {
            return 1
        }
    }
    return 0
},ec = function(a) {
    return dc(Na, a) >= 0
};
var Ba,Ca = function() {
    if (!Ba) {
        Ba = new E
    }
    return Ba
},wb = function() {
    return Ca().T()
},xb = function(a) {
    return Ca().U(a)
},Aa = xb,yb = function(a, b, c) {
    return Ca().V(a, b, c)
},sa = yb,ub = function(a, b) {
    a.appendChild(b)
},Ab = function(a) {
    if (a[U]) {
        a[U].removeChild(a)
    }
},M = function(a, b) {
    if (typeof a.contains != "undefined") {
        return a == b || a.contains(b)
    }
    if (typeof a.compareDocumentPosition != "undefined") {
        return a == b || la(a.compareDocumentPosition(b) & 16)
    }
    while (b && a != b) {
        b = b[U]
    }
    return b == a
},Da = function(a) {
    return a[ya] == 9 ? a : a.ownerDocument || a[Oa]
},vb = function(a, b, c, d) {
    if (a != null) {
        for (var f = 0,e; e = a.childNodes[f]; f++) {
            if (b(e)) {
                c.push(e);
                if (d) {
                    return
                }
            }
            vb(e, b, c, d)
        }
    }
},tb = {SCRIPT:1,STYLE:1,HEAD:1,IFRAME:1,OBJECT:1},Qa = {IMG:" ",BR:"\n"},zb = function(a, b, c) {
    if (a.nodeName in tb) {
    } else if (a[ya] == 3) {
        if (c) {
            b.push(String(a.nodeValue).replace(/(\r\n|\r|\n)/g, ""))
        } else {
            b.push(a.nodeValue)
        }
    } else if (a.nodeName in Qa) {
        b.push(Qa[a.nodeName])
    } else {
        var d = a[kb];
        while (d) {
            zb(d, b, c);
            d = d.nextSibling
        }
    }
},E = function(a) {
    this.p = a || I[Oa] || R
};
E[_P].T = function() {
    return this.p
};
E[_P].U = function(a) {
    if (wa(a)) {
        return this.p.getElementById(a)
    } else {
        return a
    }
};
E[_P].V = function(a, b, c) {
    var d = a || "*",f = c || this.p,e = f.getElementsByTagName(d);
    if (b) {
        return nb(e, function(h) {
            return mb(h.className.split(" "), b)
        })
    } else {
        return e
    }
};
E[_P].createElement = function(a) {
    return this.p.createElement(a)
};
E[_P].createTextNode = function(a) {
    return this.p.createTextNode(a)
};
E[_P].appendChild = ub;
E[_P].removeNode = Ab;
E[_P].contains = M;
var La,eb = function(a, b) {
    var c = Da(a);
    if (c.defaultView && c.defaultView.getComputedStyle) {
        var d = c.defaultView.getComputedStyle(a, "");
        if (d) {
            return d[b]
        }
    }
    if (a.currentStyle) {
        return a.currentStyle[b]
    } else {
        return a[k][b]
    }
},Tb = function(a) {
    var b;
    if (a) {
        if (a[ya] == 9) {
            b = a
        } else {
            b = Da(a)
        }
    } else {
        b = wb()
    }
    if (ka && b.compatMode != "CSS1Compat") {
        return b[xa]
    }
    return b.documentElement
},Ub = function(a) {
    var b = Da(a);
    if (!l(La)) {
        La = fb && !ec("1.8.0.11")
    }
    var c = new G(0, 0),d = Tb(b);
    if (a == d) {
        return c
    }
    var f = null,e;
    if (a.getBoundingClientRect) {
        e = a.getBoundingClientRect();
        var h = d.scrollTop,j = d.scrollLeft;
        c.x = e[aa] + j;
        c.y = e.top + h
    } else if (b.getBoxObjectFor && !La) {
        e = b.getBoxObjectFor(a);
        var n = b.getBoxObjectFor(d);
        c.x = e.screenX - n.screenX;
        c.y = e.screenY - n.screenY
    } else {
        c.x = a[na];
        c.y = a[ba];
        f = a.offsetParent;
        if (f != a) {
            while (f) {
                c.x += f[na];
                c.y += f[ba];
                f = f.offsetParent
            }
        }
        if (Ma || gb && eb(a, "position") == "absolute") {
            c.y -= b[xa][ba]
        }
        f = a[U];
        while (f && f != d) {
            c.x -= f.scrollLeft;
            if (!Ma || f.tagName != "TR") {
                c.y -= f.scrollTop
            }
            f = f[U]
        }
    }
    return c
},Vb = function(a) {
    if (eb(a, "display") != "none") {
        return new Z(a[oa], a.offsetHeight)
    }
    var b = a[k],c = b.visibility,d = b.position;
    b.visibility = "hidden";
    b.position = "absolute";
    b.display = "";
    var f = a[oa],e = a.offsetHeight;
    b.display = "none";
    b.position = d;
    b.visibility = c;
    return new Z(f, e)
},Wb = function(a, b) {
    var c = a[k];
    if ("opacity"in c) {
        c.opacity = b
    } else if ("MozOpacity"in c) {
        c.MozOpacity = b
    } else if ("KhtmlOpacity"in c) {
        c.KhtmlOpacity = b
    } else if ("filter"in c) {
        c.filter = "alpha(opacity=" + b * 100 + ")"
    }
};
var H = function() {
};
H[_P].w = false;
H[_P].A = function() {
    return this.w
};
H[_P].dispose = function() {
    if (!this.w) {
        this.w = true
    }
};
var C = {},t = {},Ta = "on",ia = "_",s = function(a, b, c, d, f) {
    if (O(b)) {
        for (var e = 0; e < b[i]; e++) {
            s(a, b[e], c, d, f)
        }
        return null
    }
    var h = N(b),j = ha(a, h, c, d, f);
    if (j in C) {
        return j
    }
    var n = A(a);
    if (!(n in t)) {
        t[n] = {};
        t[n].G = 0
    }
    if (!(h in t[n])) {
        t[n].G++;
        t[n][h] = []
    }
    t[n][h].push(j);
    var p = Db(a, j, b);
    C[j] = new Ra(c, p, a, b, d, f);
    if (b instanceof ga) {
        b.O(C[j])
    } else {
        if (a.addEventListener) {
            if (a == I || !a.H) {
                a.addEventListener(b, p, d)
            }
        } else if (a.attachEvent) {
            a.attachEvent(Ta + b, p)
        } else {
            throw J("Object {" + a + "} does not support event listeners.");
        }
    }
    return j
},Ua = function(a, b, c, d, f) {
    if (O(b)) {
        for (var e = 0; e < b[i]; e++) {
            Ua(a, b[e], c, d, f)
        }
        return null
    }
    var h = ha(a, b, c, d, f);
    return Y(h)
},Y = function(a) {
    if (!(a in C)) {
        return false
    }
    var b = C[a],c = b.src,d = b[w],f = b.proxy;
    if (d instanceof ga) {
        d.fa(b)
    } else {
        if (c.removeEventListener) {
            if (c == I || !c.H) {
                c.removeEventListener(d, f, b.capture)
            }
        } else if (c.detachEvent) {
            c.detachEvent(Ta + d, f)
        }
    }
    delete C[a];
    var e = N(d),h = A(c),j = t[h],n = j[e];
    pb(n, a);
    if (n[i] == 0) {
        j.G--;
        delete j[e]
    }
    if (j.G == 0) {
        delete t[h]
    }
    return true
},Jb = function(a, b, c) {
    var d = 0;
    if (a) {
        var f = Cb(a, b, c);
        for (var e = 0; e < f[i]; e++) {
            var h = f[e];
            if (h) {
                Y(ha(h.src, h[w], h.listener, h.capture, h.handler));
                d++
            }
        }
    } else {
        for (var j in C) {
            Y(j);
            d++
        }
    }
    return d
},Cb = function(a, b, c) {
    var d = A(a),f = [];
    if (d in t) {
        var e = t[d];
        if (b) {
            var h = N(b);
            if (b in e) {
                Sa(f, e[h], c)
            }
        } else {
            for (var j in e) {
                Sa(f, e[j], c)
            }
        }
    }
    return f
},N = function(a) {
    return a instanceof ga ? A(a) : a
},Sa = function(a, b, c) {
    var d = !l(c);
    for (var f = 0; f < b[i]; f++) {
        var e = b[f],h = C[e];
        if (d || h.capture == c) {
            a.push(h)
        }
    }
},Eb = function(a, b, c) {
    var d = A(a);
    if (d in t) {
        var f = N(b);
        if (f in t[d]) {
            return t[d][f]
        }
    }
    return null
},ta = "mouseout",Fb = function(a, b, c) {
    var d = C[a];
    if (!d) {
        return m
    }
    if (!d.src.addEventListener && !Hb(d)) {
        return m
    }
    if (!c && v.event || c && Ib(c)) {
        var f = new X(c || v.event, this);
        try {
            f.stopPropagation();
            f.i = false;
            var e,h = false;
            if (Fa) {
                e = []
            } else {
                e = Kb;
                e.length = 0;
                h = true;
                Fa = true
            }
            for (var j = f[K]; j; j = j[U]) {
                e.push(j)
            }
            var n = true;
            for (var p = e[i] - 1; !f.i && p >= 0; p--) {
                V(f, e[p]);
                n &= ua(e[p], b, true, f)
            }
            for (var p = 0; !f.i && p < e[i]; p++) {
                V(f, e[p]);
                n &= ua(e[p], b, false, f)
            }
            if (h) {
                Fa = false
            }
            return n
        } finally {
            f.dispose()
        }
    } else if (c && Gb(c)) {
        var T = new X(c, this);
        try {
            return Ea(d, T)
        } finally {
            T.dispose()
        }
    } else {
        return Ea(d, c)
    }
},Db = function(a, b, c) {
    return function(d) {
        return Fb.call(a, b, c, d)
    }
},Kb = [],Fa = false,Hb = function(a) {
    var b = N(a[w]),c = ha(a.src, a[w], a.listener, a.capture, a.handler),d = A(a.src);
    return t[d][b][0] == c
},ua = function(a, b, c, d) {
    var f = 1,e = Eb(a, b, c);
    for (var h = 0; e && h < e[i]; h++) {
        var j = C[e[h]];
        if (j.capture == c) {
            f &= Ea(j, d) !== false
        }
    }
    return la(f)
},Ea = function(a, b) {
    return a.n.call(a, b)
},Bb = [],ha = function(a, b, c, d, f) {
    if (ka) {
        var e = Bb;
        e[0] = A(a);
        e[1] = N(b);
        e[2] = A(c);
        e[3] = d ? "1" : "0";
        e[4] = f ? A(f) : "";
        return e.join(ia)
    } else {
        return A(a) + ia + N(b) + ia + A(c) + ia + la(d) + ia + (f ? A(f) : "")
    }
},Gb = function(a) {
    return va(a) && /event/i.test(a)
},Ib = function(a) {
    return ka && va(a) && l(a.srcElement) && l(a.cancelBubble) && l(a[w])
};
var ga = function() {
};
ga[_P].O = function(a) {
    throw J("Not implemented");
};
ga[_P].fa = function(a) {
    throw J("Not implemented");
};
var x = function(a, b) {
    this.type = a;
    this.target = b;
    V(this, this[da])
};
x.inherits(H);
x[_P].i = false;
x[_P].t = true;
x[_P].stopPropagation = function() {
    this.i = true
};
x[_P].preventDefault = function() {
    this.t = false
};
var X = function(a, b) {
    this.type = a[w];
    this.timestamp = new Date;
    this.target = a[da] || a.srcElement;
    V(this, b);
    ra(this, null);
    if (l(a[B])) {
        ra(this, a[B])
    } else if (this[w] == "mouseover") {
        ra(this, a.fromElement)
    } else if (this[w] == ta) {
        ra(this, a.toElement)
    }
    this.offsetX = l(a.layerX) ? a.layerX : a.offsetX;
    this.offsetY = l(a.layerY) ? a.layerY : a.offsetY;
    this.clientX = l(a.clientX) ? a.clientX : a.pageX;
    this.clientY = l(a.clientY) ? a.clientY : a.pageY;
    this.screenX = a.screenX || 0;
    this.screenY = a.screenY || 0;
    this.button = a.button;
    this.keyCode = a.keyCode || 0;
    this.charCode = a.charCode || (this[w] == "keypress" ? a.keyCode : 0);
    this.ctrlKey = a.ctrlKey;
    this.altKey = a.altKey;
    this.shiftKey = a.shiftKey;
    this.metaKey = a.metaKey;
    this.l = a
};
X.inherits(x);
X[_P].stopPropagation = function() {
    this.i = true;
    if (this.l.stopPropagation) {
        this.l.stopPropagation()
    } else {
        this.l.cancelBubble = true
    }
};
X[_P].preventDefault = function() {
    this.t = false;
    if (!this.l.preventDefault) {
        this.l.returnValue = false
    } else {
        this.l.preventDefault()
    }
};
X[_P].dispose = function() {
    if (!this.A()) {
        x[_P].dispose.call(this);
        this.l = null
    }
};
var Ra = function(a, b, c, d, f, e) {
    if (cb(a)) {
        this.$ = true
    } else if (a && typeof a.n == "function") {
        this.$ = false
    } else {
        throw J("Invalid listener argument");
    }
    this.listener = a;
    this.proxy = b;
    this.src = c;
    this.type = d;
    this.capture = !(!f);
    this.handler = e
};
Ra[_P].n = function(a) {
    if (this.$) {
        return this.listener.call(this.handler || this.src, a)
    }
    return this.listener.n.call(this.listener, a)
};
var F = function() {
};
F.inherits(H);
F[_P].X = function() {
    return null
};
F[_P].addEventListener = function(a, b, c, d) {
    s(this, a, b, c, d)
};
F[_P].removeEventListener = function(a, b, c, d) {
    Ua(this, a, b, c, d)
};
F[_P].dispatchEvent = function(a) {
    if (wa(a)) {
        a = new x(a, this)
    } else if (!(a instanceof x)) {
        var b = a;
        a = new x(a[w], this);
        Qb(a, b)
    } else {
        a.target = a[da] || this
    }
    var c = [];
    for (var d = this; d; d = d.X()) {
        c.push(d)
    }
    var f = 1;
    for (var e = c[i] - 1; !a.i && e >= 0; e--) {
        V(a, c[e]);
        f &= ua(c[e], a[w], true, a) && a.t != false
    }
    for (var e = 0; !a.i && e < c[i]; e++) {
        V(a, c[e]);
        f &= ua(c[e], a[w], false, a) && a.t != false
    }
    return la(f)
};
F[_P].dispose = function() {
    if (!this.A()) {
        H[_P].dispose.call(this);
        Jb(this)
    }
};
F[_P].H = true;
var Ob = function(a) {
    return 1 - o.pow(1 - a, 3)
},g = function(a, b, c, d) {
    F.call(this);
    if (!O(a) || !O(b)) {
        throw J("Start and end parameters must be arrays");
        return
    }
    if (a[i] != b[i]) {
        throw J("Start and end points must be the same length");
        return
    }
    this.j = a;
    this.K = b;
    this.J = c;
    this.N = d;
    this.k = []
};
g.inherits(F);
g.EventType = {PLAY:"play",BEGIN:"begin",RESUME:"resume",END:"end",STOP:"stop",FINISH:"finish",PAUSE:"pause",ANIMATE:"animate",DESTROY:"destroy"};
g.State = {STOPPED:0,PAUSED:-1,PLAYING:1};
g[_P].d = g.State.STOPPED;
g[_P].M = 0;
g[_P].a = 0;
g[_P].m = null;
g[_P].L = null;
g[_P].B = null;
g[_P].v = null;
g[_P].play = function(a) {
    if (a || this.d == g.State.STOPPED) {
        this.a = 0;
        this.k = this.j
    } else if (this.d == g.State.PLAYING) {
        return false
    }
    I.clearTimeout(this.v);
    this.m = (new Date).valueOf();
    if (this.d == g.State.PAUSED) {
        this.m -= this.J * this.a
    }
    this.L = this.m + this.J;
    this.B = this.m;
    if (this.a == 0) {
        this.e(g.EventType.BEGIN)
    }
    this.e(g.EventType.PLAY);
    if (this.d == g.State.PAUSED) {
        this.e(g.EventType.RESUME)
    }
    this.d = g.State.PLAYING;
    this.I();
    return true
};
g[_P].stop = function(a) {
    I.clearTimeout(this.v);
    this.d = g.State.STOPPED;
    if (a)this.a = 1;
    this.C(this.a);
    this.e(g.EventType.STOP);
    this.e(g.EventType.END)
};
g[_P].I = function() {
    I.clearTimeout(this.v);
    var a = (new Date).valueOf();
    this.a = (a - this.m) / (this.L - this.m);
    if (this.a >= 1)this.a = 1;
    this.M = 1000 / (a - this.B);
    this.B = a;
    if (cb(this.N)) {
        this.C(this.N(this.a))
    } else {
        this.C(this.a)
    }
    if (this.a == 1) {
        this.d = g.State.STOPPED;
        this.e(g.EventType.FINISH);
        this.e(g.EventType.END)
    } else if (this.d == g.State.PLAYING) {
        this.e(g.EventType.ANIMATE);
        var b = this.I.bind(this);
        this.v = I.setTimeout(b, 20)
    }
};
g[_P].C = function(a) {
    this.k = new Array(this.j[i]);
    for (var b = 0; b < this.j[i]; b++) {
        this.k[b] = (this.K[b] - this.j[b]) * a + this.j[b]
    }
};
g[_P].e = function(a) {
    this.dispatchEvent(new Ga(a, this))
};
var Ga = function(a, b) {
    x.call(this, a);
    this.coords = b.k;
    this.x = b.k[0];
    this.y = b.k[1];
    this.z = b.k[2];
    this.duration = b.J;
    this.progress = b.a;
    this.fps = b.M;
    this.state = b.d;
    this.anim = b
};
Ga.inherits(x);
Ga[_P].F = function() {
    return ob(this.coords, o.round)
};
var q = function(a, b, c, d, f) {
    g.call(this, b, c, d, f);
    this.element = a
};
q.inherits(g);
var ja = function(a, b, c, d, f) {
    q.apply(this, arguments);
    if (b[i] != 2 || c[i] != 2) {
        throw"[goog.fxdhtml.Slide] Start and end points must be 2D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.da, false, this)
};
ja.inherits(q);
ja[_P].da = function(a) {
    qa(this.element[k], o.round(a.x) + "px");
    this.element[k].top = o.round(a.y) + "px"
};
var bb = function(a, b, c, d) {
    var f = [a[na],a[ba]];
    s(this, g.EventType.BEGIN, this.ja, false, this);
    ja.call(this, a, f, b, c, d)
};
bb.inherits(ja);
bb[_P].ja = function(a) {
    this.j = [this.element[na],this.element[ba]]
};
var Ha = function(a, b, c, d, f) {
    q.apply(this, arguments);
    if (b[i] != 2 || c[i] != 2) {
        throw"[goog.fxdhtml.Slide] Start and end points must be 2D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.s, false, this);
    this.ba = o.max(this.K[0], this.j[0]);
    this.aa = o.max(this.K[1], this.j[1])
};
Ha.inherits(q);
Ha[_P].s = function(a) {
    this.R(o.round(a.x), o.round(a.y), this.ba, this.aa);
    W(this.element[k], o.round(a.x) + "px");
    this.element[k].marginLeft = o.round(a.x) - this.ba + "px";
    this.element[k].marginTop = o.round(a.y) - this.aa + "px"
};
Ha[_P].R = function(a, b, c, d) {
    this.element[k].clip = "rect(" + (d - b) + "px " + c + "px " + d + "px " + (c - a) + "px)"
};
var ab = function(a, b, c, d, f) {
    q.apply(this, arguments);
    if (b[i] != 2 || c[i] != 2) {
        throw"[goog.fx.dom.Scroll] Start and end points must be 2D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.ia, false, this)
};
ab.inherits(q);
ab[_P].ia = function(a) {
    this.element.scrollLeft = o.round(a.x);
    this.element.scrollTop = o.round(a.y)
};
var Ya = function(a, b, c, d, f) {
    q.apply(this, arguments);
    if (b[i] != 2 || c[i] != 2) {
        throw"[goog.fx.dom.Resize] Start and end points must be 2D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.s, false, this)
};
Ya.inherits(q);
Ya[_P].s = function(a) {
    W(this.element[k], o.round(a.x) + "px");
    pa(this.element[k], o.round(a.y) + "px")
};
var $a = function(a, b, c, d, f) {
    q.call(this, a, [b], [c], d, f);
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.ha, false, this)
};
$a.inherits(q);
$a[_P].ha = function(a) {
    W(this.element[k], o.round(a.x) + "px")
};
var Za = function(a, b, c, d, f) {
    q.call(this, a, [b], [c], d, f);
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.ga, false, this)
};
Za.inherits(q);
Za[_P].ga = function(a) {
    pa(this.element[k], o.round(a.x) + "px")
};
var z = function(a, b, c, d, f) {
    if (db(b))b = [b];
    if (db(c))c = [c];
    q.call(this, a, b, c, d, f);
    if (b[i] != 1 || c[i] != 1) {
        throw"[goog.fx.dom.Fade] Start and end points must be 1D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.S, false, this)
};
z.inherits(q);
z[_P].S = function(a) {
    Wb(this.element, a.x)
};
z[_P].show = function(a) {
    this.element[k].display = ""
};
z[_P].hide = function(a) {
    this.element[k].display = "none"
};
var Mb = function(a, b, c) {
    z.call(this, a, 1, 0, b, c)
};
Mb.inherits(z);
var Lb = function(a, b, c) {
    z.call(this, a, 0, 1, b, c)
};
Lb.inherits(z);
var Nb = function(a, b, c) {
    z.call(this, a, 1, 0, b, c);
    s(this, g.EventType.BEGIN, this.show, false, this);
    s(this, g.EventType.END, this.hide, false, this)
};
Nb.inherits(z);
var Xa = function(a, b, c) {
    z.call(this, a, 0, 1, b, c);
    s(this, g.EventType.BEGIN, this.show, false, this)
};
Xa.inherits(z);
var Va = function(a, b, c, d, f) {
    q.apply(this, arguments);
    if (b[i] != 3 || c[i] != 3) {
        throw"[goog.fx.dom.BgColorTransform] Start and end points must be 3D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.u, false, this)
};
Va.inherits(q);
Va[_P].u = function(a) {
    var b = "rgb(" + a.F().join(",") + ")";
    this.element[k].backgroundColor = b
};
var Wa = function(a, b, c, d, f) {
    q.apply(this, arguments);
    if (b[i] != 3 || c[i] != 3) {
        throw"[goog.fx.dom.ColorTransform] Start and end points must be 3D";
        return
    }
    var e = [g.EventType.BEGIN,g.EventType.ANIMATE,g.EventType.END];
    s(this, e, this.u, false, this)
};
Wa.inherits(q);
Wa[_P].u = function(a) {
    var b = "rgb(" + a.F().join(",") + ")";
    this.element[k].color = "rgb(" + b + ")"
};
var y = function(a) {
    this.Y = a
};
y.inherits(H);
y.c = null;
y.f = null;
y[_P].listen = function(a, b, c, d, f) {
    if (O(b)) {
        for (var e = 0; e < b[i]; e++) {
            this.listen(a, b[e], c, d, f)
        }
        return
    }
    var h = s(a, b, c || this, d || false, f || this.Y || this);
    if (this.c) {
        this.c[h] = true
    } else if (this.f) {
        this.c = {};
        this.c[this.f] = true;
        this.f = null;
        this.c[h] = true
    } else {
        this.f = h
    }
};
y[_P].unlisten = function(a, b, c, d, f) {
    if (!this.f && !this.c) {
        return
    }
    if (O(b)) {
        for (var e = 0; e < b[i]; e++) {
            this.unlisten(a, b[e], c, d, f)
        }
        return
    }
    var h = ha(a, b, c || this, d || false, f || this.Y || this);
    Y(h);
    if (this.c) {
        Rb(this.c, h)
    } else if (this.f == h) {
        this.f = null
    }
};
y[_P].ea = function() {
    if (this.c) {
        for (var a in this.c) {
            Y(a)
        }
    } else if (this.f) {
        Y(this.f)
    }
};
y[_P].dispose = function() {
    if (!this.A()) {
        H[_P].dispose.call(this);
        this.ea()
    }
};
y[_P].n = function(a) {
    throw J("EventHandler.handleEvent not impemented");
};
var fc = [],P = function(a, b, c, d, f, e) {
    y.call(this);
    this.el = a;
    this.mouseTimeout = null;
    this.frames = b - 1;
    this.ySpritePosition = c;
    this.ma = d;
    this.mouseTimeoutTime = f;
    this.D = null;
    this.Z = sa("span", "", this.el)[0];
    this.frameWidth = Vb(this.Z)[D];
    this.o = e ? new Q(this, e) : m;
    this.g = -1;
    this.h = 1;
    this.listen(this.el, "mouseover", this.r);
    this.listen(this.el, ta, this.q);
    this.listen(R, ta, this.la);
    fc.push(this)
};
P.inherits(y);
P[_P].play = function(a, b) {
    if (a !== m) {
        this.g = a;
        this.mouseTimeout = v.clearTimeout(this.mouseTimeout);
        this.D = v.clearTimeout(this.D)
    }
    if (b !== m) {
        this.h = b
    }
    var c = "-" + this.frameWidth * this.h + "px " + this.ySpritePosition;
    this.Z[k].backgroundPosition = c;
    if (this.g == 1 && this.h >= this.frames) {
        this.h = this.frames - 1;
        return
    } else if (this.g == -1 && this.h <= 0) {
        this.h = 1;
        return
    } else if (this.o && this.g == 1 && !this.o.isShowing) {
        this.o.show()
    } else if (this.o && this.g == -1 && this.o.isShowing) {
        this.o.hide()
    }
    this.D = v.setTimeout(this[ca].bind(this, m, this.h += this.g), this.ma)
};
P[_P].r = function(a) {
    if (a[B] && !M(a[K], a[B])) {
        this.mouseTimeout = v.clearTimeout(this.mouseTimeout);
        if (this.g == -1) {
            this.mouseTimeout = v.setTimeout(this[ca].bind(this, 1, m), this.mouseTimeoutTime / 3)
        }
    }
};
P[_P].q = function(a) {
    if (a[B] && M(a[K], a[da]) && !M(a[K], a[B])) {
        this.mouseTimeout = v.clearTimeout(this.mouseTimeout);
        if (this.g == 1) {
            this.mouseTimeout = v.setTimeout(this[ca].bind(this, -1, m), this.mouseTimeoutTime)
        }
    }
};
P[_P].la = function(a) {
    if (a[B] || this.h == 1) {
        return
    }
    this.mouseTimeout = v.setTimeout(this[ca].bind(this, -1, m), this.mouseTimeoutTime)
};
var Q = function(a, b) {
    y.call(this);
    this.b = a;
    var c = Aa(b);
    this.el = c.cloneNode(true);
    this.el.id = this.b.el.id + "-tt";
    this.el.className = c.className;
    this.el[k].display = "none";
    var d = sa("", b + "-text", this.el)[0];
    this.ka = d.appendChild(R.createElement("span"));
    this.ka.appendChild(R.createTextNode(this.b.el.getAttribute("title")));
    this.b.el.setAttribute("title", "");
    R[xa].appendChild(this.el);
    this.isShowing = false;
    this.listen(this.el, "mouseover", this.r);
    this.listen(this.el, ta, this.q)
};
Q.inherits(y);
Q[_P].W = function() {
    var a = this.el[k];
    a.visibility = "hidden";
    a.display = "block";
    var b = {height:this.el.offsetHeight,width:this.ka[oa]};
    a.display = "none";
    a.visibility = "visible";
    return b
};
Q[_P].r = function(a) {
    if (a[B] && !M(a[K], a[B])) {
        v.clearTimeout(this.b.mouseTimeout)
    }
};
Q[_P].q = function(a) {
    if (a[B] && M(a[K], a[da]) && !M(a[K], a[B])) {
        v.clearTimeout(this.b.mouseTimeout);
        this.b.mouseTimeout = v.setTimeout(this.b[ca].bind(this.b, -1, this.b.frames - 2), this.b.mouseTimeoutTime)
    }
};
Q[_P].show = function() {
    this.isShowing = true;
    var a = Ub(this.b.el),b = a.x,c = a.y,d = this.W();
    d.width += 14;
    W(this.el[k], d[D] + "px");
    b -= (d[D] - this.b.el[oa]) / 2;
    c -= d[L] - 3;
    qa(this.el[k], b + "px");
    this.el[k].top = c + "px";
    var f = new ja(this.el, [b,c + 10], [b,c], 500, Ob);
    f.play();
    var e = new Xa(this.el, 500);
    e.play()
};
Q[_P].hide = function() {
    this.isShowing = false;
    this.el[k].display = "none"
};
var r = {};
r.svcToolbarFrames = 7;
r.svcToolbarAnimationSpeed = 100;
r.svcToolbarMouseoutTime = 100;
r.init = function() {
    if (ka && Na < 5.5) {
        return
    }
    var h = sa("a", "", Aa("svc-toolbar"));
    for (var b = 0,c = h[i]; b < c; b++) {
        var d = h[b],e = d.id.replace("-i", "");
        new P(d, r.svcToolbarFrames, r.svcToolbarYSpritePosition[e], r.svcToolbarAnimationSpeed, r.svcToolbarMouseoutTime, "tt")
    }
    try {
        R.execCommand("BackgroundImageCache", false, true)
    } catch(j) {
    }
};