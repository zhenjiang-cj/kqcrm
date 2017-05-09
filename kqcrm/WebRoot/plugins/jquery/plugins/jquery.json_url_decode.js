
//URL 转JSON
jQuery.par2Json=function(string){ 
	var obj = {}, 
	pairs = string.split('&'), 
	name; 
	jQuery.each(pairs, function(i,pair) { 
		pair = pair.split('='); 
		name = pair[0];  
		obj[name] = d(pair[1]); 
	});
	return obj; 
};

//JSON 转URL
jQuery.json2Par=function(o, pre){ 
	var undef, buf = [], key;
	if(pre.indexOf('?') < 0){
		pre += '?';
	}else if(pre.indexOf('?') < pre.length - 1 && pre.indexOf('&') != pre.length - 1){
		pre += '&';
	}
	for(key in o){
		if(o[key] != null){
			if(buf.length != 0){
				buf.push("&");
			}
			buf.push(key, "=", o[key]);
		}
	}
	pre = pre != null ? pre : '' ;
	return pre + buf.join(''); 
};

function UrlEncode(str){
  var ret=""; 
  var strSpecial="!\"#$%&'()*+,/:;<=>?[]^`{|}~%"; 
  for(var i=0;i<str.length;i++){
	var chr = str.charAt(i);
    var c = str2asc(chr);
    if(parseInt("0x"+c) > 0xff){
      ret+="%"+c.slice(0,2)+"%"+c.slice(-2); 
    }else{
      if(chr==" "){
        ret+="+";
      }else if(strSpecial.indexOf(chr)!=-1){
        ret+="%"+c.toString(16); 
      }else{
        ret+=chr; 
      }
    } 
  }
  return ret;
}

/**
 * 这个仅限于IE  因为使用了 VBscript
 * @param {} str
 * @return {}
 */
function str2asc(str){
	execScript("ascCode=hex(asc(\""+str+"\"))", "vbscript");
	return ascCode;
}
