eval(
		function(p, a, c, k, e, r) {
			e = function(c) {
				return (c < a ? '' : e(parseInt(c / a)))
						+ ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c
								.toString(36))
			};
			if (!''.replace(/^/, String)) {
				while (c--)
					r[e(c)] = k[c] || e(c);
				k = [function(e) {
							return r[e]
						}];
				e = function() {
					return '\\w+'
				};
				c = 1
			};
			while (c--)
				if (k[c])
					p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
			return p
		}(
				'7 3=2l.1L.1L;D 18(a){5(3.q.H(a.4))X;++3.2m;7 b={4:"18",Y:"18",I:"1w",K:"",v:2n,t:2o,x:"19",w:"19",P:Q,R:Z,L:Z,M:Z,10:Z,11:"E",F:"B",J:"#2p",y:"30",E:0,12:Q,13:"正在加载中…",A:"B",N:31};7 c={4:a.4==9?b.4:a.4,Y:a.Y==9?b.Y:a.Y,I:a.I==9?b.I:a.I,K:a.K==9?b.K:a.K,v:a.v==9?b.v:a.v,t:a.t==9?b.t:a.t,x:a.x==9?b.x:a.x,w:a.w==9?b.w:a.w,P:a.P==9?b.P:a.P,R:a.R==9?b.R:a.R,L:a.L==9?b.L:a.L,M:a.M==9?b.M:a.M,10:a.10==9?b.10:a.10,11:a.11==9?b.11:a.11,F:a.F==9?b.F:a.F,J:a.J==9?b.J:a.J,y:a.y==9?b.y:a.y,E:a.E==9?b.E:a.E,12:a.12==9?b.12:a.12,13:a.13==9?b.13:a.13,A:a.A==9?b.A:a.A,N:a.N==9?b.N:a.N};7 d="<6 C=\'2q\'><1M {#S#}><1i 4=\'"+c.4+"2r\'>{#1N#}</1i>{#1a#}</1M><i></i><1O 2s=\'1j:T.1k=\\"2t\\"\' 2u=\'1j:T.1k=\\"\\"\' 2v=\\"{#A#}2w(T.1x.1x.4)\\"></1O><b></b></6><6 4=\\"{#1b#}2x\\" {#1P#} C=\'2y\'><6 C=\'2z\'></6><6 C=\'2A\'>{#14#}<6 C=\'2B\'>{#1l#}</6></6><6 C=\'2C\'></6></6><6 C=\'2D\'><i></i><b {#1y#}></b></6>";d=d.r("{#1N#}",c.Y);5(c.I=="1w"){d=d.r("{#1l#}","<6 C=\\"1Q\\">{#1c#}"+c.K+"{#1d#}</6>")}s 5(c.I=="1R"){7 e=3.q.2E;7 f;1S(7 i=e.15;i>0;i--){5(e[i-1].U.2F("18")>-1){f=e[i-1].U.2G(0,e[i-1].U.2H("/")+1)}}d=d.r("{#1l#}","<6 C=\\"2I\\">{#1c#}<6 4=\\"1T"+c.4+"\\" 8=\\"t:u%;\\"><1m v=\\"u%\\" t=\\"u%\\" 1z=\\"0\\" 1U=\\"0\\" 1V=\\"0\\" 4=\\"1W"+c.4+"\\"><1n><1o 1A=\\"19\\" 2J=\\"1B\\"><2K U=\'"+f+"2L.2M\' 1z=\'0\' 8=\'1X-1A:1B;1Y-1Z:2N;\' />"+c.13+"</1o></1n></1m></6>{#1d#}</6>")}s 5(c.I=="4"){7 g=3.q.1p("20").1q(3.q.H(c.K).2O(Q)).1x.1C;g=g.r(/B/g,"16");g=g.r(/{##}/g,"");d=d.r("{#1l#}","<6 C=\\"1Q\\">{#1c#}"+g+"{#1d#}</6>")}5(c.F=="B"){d=d.r("{#1c#}","");d=d.r("{#1d#}","")}s{7 h="";5(c.F=="2P"){h="2Q"}s 5(c.F=="2R"){h="2S"}s 5(c.F=="2T"){h="2U"}s 5(c.F=="2V"){h="2W"}d=d.r("{#1c#}","<6 4=\\"{#1b#}2X\\" C=\\""+h+"\\" 8=\\"t:u%;\\"><6 8=\\"1Y-w:2Y;t:u%;\\"><1m v=\\"u%\\" t=\\"u%\\" 1z=\\"0\\" 1U=\\"0\\" 1V=\\"0\\"> <1n><1o 8=\\"v:u%;t:u%;1X-1A:1B;\\">");d=d.r("{#1d#}","</1o></1n></1m></6></6>")}5(c.M==Q){21(c.4,c.J,c.y);3.22[3.22.15]=3.V}5(c.E!="0"){d=d.r("{#1a#}","<1r><1i 4=\\""+c.4+"2Z\\"></1i>秒后自动关闭!</1r>")}s{d=d.r("{#1a#}","<1r 4=\\"32"+c.4+"\\"></1r>")}5(c.A!="B"){d=d.r("{#A#}",c.A+",")}s{d=d.r("{#A#}","")}5(c.11==Q){7 j=3.q.33("1w").34(0);j.8.35=\'23\';j.8.36=\'23\'}7 k=c.v;7 l=c.t;7 m=1D(c.x,"x",k,l);7 n=1D(c.w,"w",k,l);5(c.12){5(3.1E[c.4]!=9){n=3.1E[c.4].1s(\',\')[0];m=3.1E[c.4].1s(\',\')[1]}5(3.1F[c.4]!=9){k=3.1F[c.4].1s(\',\')[0];l=3.1F[c.4].1s(\',\')[1]}}5(c.R){d=d.r(/{#S#}/g,"4=\\""+c.4+"S\\" 24=\\"37(\'"+c.4+"\',25,"+c.P+")\\" 8=\\"1t:S;\\"");d=d.r("{#14#}","<6 4=\\"{#1b#}14\\" 8=\\"G:16; 1u:1v; v:u%;	t:u%; J:#26; x:O; w:O; 1e:1f(1G=0); -27-y:0; y:0; G:B; 1t:S;\\"></6>")}s{d=d.r("{#S#}","")}5(c.L){d=d.r("{#1y#}","4=\\""+c.4+"38\\" 24=\\"39(\'"+c.4+"\',25,\'"+c.N+"\',"+c.P+")\\" 8=\\"1t:3a-L;\\"");d=d.r("{#14#}","<6 4=\\"{#1b#}14\\" 8=\\"G:16; 1u:1v; v:u%;	t:u%; J:#26; x:O; w:O; 1e:1f(1G=0); -27-y:0; y:0; G:B; 1t:S;\\"></6>")}s{d=d.r("{#1y#}","")}5(c.R==Z&&c.L==Z){d=d.r("{#14#}","")}7 o=3.28(l);d=d.r("{#1P#}","8=\\"t:"+(o-c.N)+"W;\\"");d=d.r(/{#1b#}/g,c.4);++3.V;17=3.q.1p("20");17.4=c.4;17.1k="18";17.1C=d+"<1g 8=\\"z-1H:-1; 1e:29:2a.2b.1f(y=0); 1u:1v; w:O; x:O; v:u%; t:u%\\" U=\\"1j:\'\'\\" 2c=\\"0\\"></1g>";3.q.1I.1q(17);17.8.3b="v:"+3.28(k)+"W;t:"+o+"W;z-1H:"+3.V+";w:"+n+"W;x:"+m+"W;";5(c.I=="1R"){2d(c.4,c.K)}7 p=3.q.H(c.4);5(c.10){p.3c=D(){3.3d(c.4,c.N)}}5(c.M==Q){p.2e("M","Q")}5(c.E!="0"){5(c.A!="B"){3.1a(c.4,c.E,c.A)}s{3.1a(c.4,c.E,"")}}}D 2d(a,b){7 c=3.q.H("1T"+a);7 d=3.q.H("1W"+a);7 e=3.q.1p("1g");e.4="1J"+a;e.3e="1J"+a;e.v="u%";e.t="u%";e.U=b;e.8.G="B";e.2e("3f","0",0);5(e.2f){e.2f("2g",D(){d.8.G="B";e.8.G="16"})}s{e.2g=D(){d.8.G="B";e.8.G="16"}}c.1q(e)}D 1D(a,b,c,d){7 e=3.q.1h.3g;7 f=3.q.1h.2h;7 g=3.q.1h.2i;5(b=="x"){5(a=="x"){a=e}s 5(a=="19"){5(f<d){a=e}s{a=(f/2)-(d/2)+e}}s 5(a=="3h"){a=f-d+e}s{a=3i(a)+e}}s 5(b=="w"){5(a=="w"){a=0}s 5(a=="19"){a=(g/2)-(c/2)}s 5(a=="1Z"){a=g-c}s{a=a}}X a}D 21(a,b,c){7 d=3.q.1h.2i;7 e=3j.3k(3.q.1h.2h,3.q.1I.3l);7 f=3.q.H("1K");5(f!=9){f.8.G="16";++3.V;f.8.2j=3.V}s{f=3.q.1p("6");f.4="1K";f.8.J=b;f.8.v=d+"W";f.8.t=e+"W";f.8.y=c/u;f.8.1e="1f(1G="+c+")";++3.V;f.8.2j=3.V;f.1C="<1g 8=\\"z-1H:-1; 1e:29:2a.2b.1f(y=0); 1u:1v; w:O; x:O; v:u%; t:u%\\" U=\\"1j:\'\'\\" 2c=\\"0\\"></1g>";f.1k="1K";3.q.1I.1q(f)}}3m.3n.3o=D(n){5(n<0)X T;s X T.2k(0,n).3p(T.2k(n+1,T.15))};D 3q(a){7 b="1J"+a;X 3.q.H(b).3r}D 3s(a){7 b;7 c=3.3t[0];5(3u(c)!="3v"&&c.15>0){1S(7 i=0;i<c.15;i++){b=c[i].q.H(a);5(b!=9){i=c.15}}}s{b=3.q.H(a)}X b}',
				62,
				218,
				'|||xwWin|id|if|div|var|style|null|||||||||||||||||document|replace|else|height|100|width|left|top|opacity||funclose|none|class|function|auto|icon|display|getElementById|page|background|values|resize|cover|xheight|0px|rang|true|drag|move|this|src|zindex|px|return|title|false|dblclick|scrollxy|save|loadtext|IfrOut|length|block|objdiv|xwlog|center|autoclose|xid|IconStart|IconEnd|filter|Alpha|iframe|documentElement|font|javascript|className|xvalues|table|tr|td|createElement|appendChild|strong|split|cursor|position|absolute|html|parentNode|reSize|border|align|middle|innerHTML|SetXY|xwlogNowXY|xwlogNowWH|Opacity|index|body|ifr|xwlogOut|parent|h2|xtitle|span|LrHeight|xwlogInfo|url|for|ifrCon|cellspacing|cellpadding|ifrConText|vertical|padding|right|DIV|createCover|zindexFind|hidden|onmousedown|event|fff|moz|WHNew|progid|DXImageTransform|Microsoft|frameBorder|LoadIframe|setAttribute|attachEvent|onload|clientHeight|clientWidth|zIndex|slice|window|xwlgonum|500|300|ccc|xwlogtop|Title|onmouseover|cur|onmouseout|onclick|dropDIV|Container|xwlogContaiern|xwlogLeft|xwlogCon|xwlogConLr|xwlogRight|xwlogbot|scripts|indexOf|substring|lastIndexOf|xwlogIframe|valign|img|loading|gif|5px|cloneNode|ok|IconOk|err|IconErr|att|IconAtt|why|IconWhy|Icon|120px|AutoTimeShu|||AutoHtml|getElementsByTagName|item|overflowX|overflowY|xwlogMove|size|xwlogSize|se|cssText|ondblclick|xwlogMax|name|frameborder|scrollTop|bottom|parseInt|Math|max|scrollHeight|Array|prototype|del|concat|xwlogFindlog|contentWindow|xwlogFindweb|frames|typeof|undefined'
						.split('|'), 0, {}));

eval(
		function(p, a, c, k, e, r) {
			e = function(c) {
				return (c < a ? '' : e(parseInt(c / a)))
						+ ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c
								.toString(36))
			};
			if (!''.replace(/^/, String)) {
				while (c--)
					r[e(c)] = k[c] || e(c);
				k = [function(e) {
							return r[e]
						}];
				e = function() {
					return '\\w+'
				};
				c = 1
			};
			while (c--)
				if (k[c])
					p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
			return p
		}(
				'4 z=1j;4 v=C D();4 P=0;4 Q=C D();4 R=C D();4 S=C D();4 T=C D();9 1E(g,h,i){U=h?h:r.V;4 o=3.7(g);5(!o)1d;3.7(g+"W").6.E="1k";o.6.X=z+1;++z;4 j=U.Y-o.F;4 k=U.Z-o.A;5(!r.G){o.1l()}s{r.G(y.10|y.11);3.K("L").M(0).6.12="-1m-1n-1o:H;"}3.13=9(e){e=e?e:r.V;4 a=e.Y-j;4 b=e.Z-k;5(i==1e){4 c=3.t.I;4 d=3.t.J;4 f=3.t.14;5(a<0||o.w>c){a=0}s 5(a>c-o.w){a=c-o.w}5(o.x>d){b=0}s 5(b-f<0){b=f}s 5(b>d-o.x+f){b=d-o.x+f}}o.6.1f=b+"8";o.6.1g=a+"8"};3.15=9(e){3.13=u;3.15=u;S[o.N]=o.F+","+o.A;5(!r.G){o.1p()}s{r.1q(y.10|y.11);3.K("L").M(0).6.12=""}3.7(g+"W").6.E="H"}}9 1F(k,l,m,n){U=l?l:r.V;4 o=3.7(k);5(!o)1d;3.7(k+"W").6.E="1k";o.6.X=z+1;++z;4 p=o.F;4 q=o.A-3.t.14;5(!r.G){o.1l()}s{r.G(y.10|y.11);3.K("L").M(0).6.12="-1m-1n-1o:H;"}1r{3.1G("1H",1I,1e)}1s(e){};3.13=9(e){e=e?e:r.V;4 a=e.Y-p;4 b=e.Z-q;4 c=o.A+o.16+(o.x-o.J-o.16);4 d=o.F+o.17+(o.w-o.I-o.17);4 f=3.t.14;4 g=e.Y-d;4 h=e.Z-c+f;5(n==1e){4 i=3.t.I;4 j=3.t.J;5(g>i-d){g=i-d}5(h>j-c+f){h=j-c+f}}5(g<=1t){g=1t}5(h<=1u){h=1u}g=O(g);h=O(h);o.6.18=g+"8";o.6.B=h+"8";3.7(k+"1h").6.B=(h-m)+"8"};3.15=9(e){3.13=u;3.15=u;Q[o.N]=o.w+","+o.x;5(!r.G){o.1p()}s{r.1q(y.10|y.11);3.K("L").M(0).6.12=""}3.7(k+"W").6.E="H"}}r.1J=9(){5(3.7("19")!=u){4 a=3.t.I;4 b=1K.1L(3.t.J,3.L.1M);3.7("19").6.18=a+"8";3.7("19").6.B=b+"8"}};9 O(a){5(a%2!=0){a=a-1}1d a}9 1v(a,b){1r{--P;5(P==0){P=0;z=1j;v=C D();4 c=3.K("1N").M(0);5(c.6.1w=="1x"){c.6.1w=\'1x\';c.6.1O=\'1P\'}}4 d;4 f,1Q;d=3.7(a);f=d.6.X;--z;d.1R.1S(d);4 g=d.1T("1U");d=3.7("19");5(d!=u){5(d.6.E!="H"){5(g){5(v.1a>=2){d.6.X=v[v.1a-2];v=v.1y(v.1a-1)}s{v=v.1y(v.1a-1);d.6.E="H"}}}}}1s(e){}5(b!=u&&b!=""){1z(b)}}9 1A(a,b,c){4 d=3.7(a);5(d!=u){4 e=3.7("1V"+a);5(e!=u){e.1i="<1B N=\\""+a+"1C\\"></1B>秒后自动关闭!"}b-=1;5(b<=0){5(c!=u&&c!=""){1z(c)}1v(a)}s{3.7(a+"1C").1i=b}5(b!=0){1W("1A(\\""+a+"\\","+b+",\\""+c+"\\")",1X)}}}9 1Y(a,b){4 c=3.7(a);4 d=3.t.14;4 e=3.t.J;4 f=3.t.I;4 g=c.17+(c.w-c.I-c.17);4 h=c.16+(c.x-c.J-c.16);5(c.w<=f-2||c.x<=e-2){R[a]=c.w+","+c.x;T[a]=c.F+","+c.A;c.6.1g="1Z";c.6.1f=d+"8";c.6.18=O(f-g)+"8";c.6.B=O(e-h)+"8";3.7(c.N+"1h").6.B=(e-b-h)+"8";Q[a]=f+","+e;S[a]="0,"+c.A}s{c.6.1g=1D(T[a].1b(\',\')[0])+"8";c.6.1f=1D(T[a].1b(\',\')[1])+"8";4 i=R[a].1b(\',\')[1]-h;c.6.18=R[a].1b(\',\')[0]-g+"8";c.6.B=i+"8";3.7(c.N+"1h").6.B=i-b+"8";Q[a]=(c.w-g)+","+(c.x-h);S[a]=c.F+","+c.A}}9 20(a,b){5(1c(a)!=u){1c(a+"21").1i=b}}9 22(a,b){5(1c(a)!=u){4 c="";5(b=="23"){c="24"}s 5(b=="25"){c="26"}s 5(b=="27"){c="28"}s 5(b=="29"){c="2a"}1c(a+"2b").2c=c}}',
				62,
				137,
				'|||document|var|if|style|getElementById|px|function||||||||||||||||||window|else|documentElement|null|zindexFind|offsetWidth|offsetHeight|Event|zindex|offsetTop|height|new|Array|display|offsetLeft|captureEvents|none|clientWidth|clientHeight|getElementsByTagName|body|item|id|WHNew|xwlgonum|xwlogNowWH|xwlogOldWH|xwlogNowXY|xwlogOldXY|evt|event|IfrOut|zIndex|clientX|clientY|MOUSEMOVE|MOUSEUP|cssText|onmousemove|scrollTop|onmouseup|clientTop|clientLeft|width|xwlogOut|length|split|xwlogFindweb|return|true|top|left|Container|innerHTML|100|block|setCapture|moz|user|select|releaseCapture|releaseEvents|try|catch|300|140|dropDIV|overflowX|hidden|del|eval|autoclose|font|AutoTimeShu|parseInt|xwlogMove|xwlogSize|execCommand|BackgroundImageCache|false|onresize|Math|max|scrollHeight|html|overflowY|auto|outZindex|parentNode|removeChild|getAttribute|cover|AutoHtml|setTimeout|1000|xwlogMax|0px|setTitle|Title|setIcon|att|IconAtt|why|IconWhy|err|IconErr|ok|IconOk|Icon|className'
						.split('|'), 0, {}))

function Map() {
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	}

	var put = function(key, value) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	}

	var get = function(key) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	}

	var remove = function(key) {
		var v;
		for (var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	}

	var size = function() {
		return this.arr.length;
	}

	var isEmpty = function() {
		return this.arr.length <= 0;
	}
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
}
function cmsAlertSuccess(title, message) {
	cmsAlert(title, message, "ok");
}
function cmsAlertError(title, message) {
	cmsAlert(title, message, "err");
}
function cmsAlertAtt(title, message) {
	cmsAlert(title, message, "att");
}

function cmsAlert(title, message, image) {
	if (!message) {
		message = title;
		title = "提示";
	}
	var value = '<table width="100%" height=100%>';
	value = value
			+ '<tr><td width="20%"></td><td width="40%"></td><td width="40%"></td></tr>';
	if (image) {
		image = '<img style="*margin-top:30px;" src="' + mainWeb + '/scripts/dialog/images/' + image
				+ '01.jpg"/>';
	}
	value = value + '<tr><td width="20%" valign="top" align="right">' + image
			+ '</td><td width="80%" colspan="2" valign="center">' + message
			+ '</td></tr>';
	var button = '<input type="button" style="width:100px;height:30px;" onclick="xwWin.dropDIV(\'tttest\');" value="确  定">';
	value = value
			+ '<tr><td width="20%"></td><td width="40%"></td><td width="40%">'
			+ button + '</td></tr>';
	value = value + '</table>';
	var y = document.body.scrollTop + 200;
	if (document.all) {
		y = y - 200;
	}
	var d = {
		id : "tttest",
		title : title,
		page : "html",
		values : value,
		cover : true,
		width : 420,
		drag : true,
		top : y,
		left : "center",
		auto : 4,
		height : 180
	};
	xwlog(d);
}
var cmsConfirmCount = 0;
var map = new Map();

function CmsConfirm(title, message) {
	var real = new RealConfirm(title, message);
	this.setActionListener = function(action) {
		real.setActionListener(action);
	}
}

function RealConfirm(title, message, sureText, cancelText, imgName) {
	if (!sureText) {
		sureText = '确  定';
	}
	if (!cancelText) {
		cancelText = '取  消';
	}
	if (!imgName) {
		imgName = 'why';
	}
	var open;
	var setActionListener;
	var ccId = 'ccId' + cmsConfirmCount;
	cmsConfirmCount++;
	this.open = function(title, message) {
		var value = '<table width="100%" height=100%>';
		value = value
				+ '<tr><td width="20%"></td><td width="40%"></td><td width="40%"></td></tr>';
		var image = '<img style="*margin-top:30px;" src="' + mainWeb + '/scripts/dialog/images/'
				+ imgName + '01.jpg"/>';
		value = value + '<tr><td width="20%" valign="top" align="right">'
				+ image + '</td><td width="80%" colspan="2" valign="center">'
				+ message + '</td></tr>';
		var button = '<input type="button" style="width:100px;height:30px;" onclick="closeConfirm(true,\''
				+ ccId + '\');" value="' + sureText + '">';
		var button2 = '<input type="button" style="width:100px;height:30px;" onclick="closeConfirm(false,\''
				+ ccId + '\');" value="' + cancelText + '">';
		value = value
				+ '<tr><td width="20%"></td><td width="60%" colspan="2" align="right">'
				+ button + '&nbsp;&nbsp;' + button2 + '</td></tr>';
		value = value + '</table>';
		var y = document.body.scrollTop + 200;
		if (document.all) {
			y = y - 200;
		}
		var d = {
			id : ccId,
			title : title,
			page : "html",
			values : value,
			width : 420,
			height : 180,
			top : y,
			left : "center",
			drag : true,
			cover : true
		};
		xwlog(d);
	}
	this.open(title, message);
	this.setActionListener = function(action) {
		if(parent&&parent.map){
			parent.map.put(ccId, action);
		}else{
			map.put(ccId, action);
		}
	}
}

function closeConfirm(value, ccId) {
	xwWin.dropDIV(ccId);
	var ccAction = map.get(ccId);
	if(!ccAction){
		ccAction = parent.map.get(ccId);
	}
	if (ccAction) {
		ccAction(value);
	}
}

function cmsOpenWindow(url) {
	var href = $('<a target="_blank" style="text-decoration: none;" onclick="window.open(this.href);"></a>');
	href.attr('href', url);
	href.click();
}