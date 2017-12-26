//javascript
var checkIdCard = function(card){
    var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
        21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
        33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
        42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
        51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
        63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
    };
    //检查号码是否符合规范，包括长度，类型
    isCardNo = function(card){
        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
        return reg.test(card);
    };

    //取身份证前两位,校验省份
    checkProvince = function(card)
    {
        var province = card.substr(0,2);
        return vcity[province] != undefined;
    };

    //检查生日是否正确
    checkBirthday = function(card)
    {
        var len = card.length;
        //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
        if(len == '15')
        {
            var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
            var arr_data = card.match(re_fifteen);
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date('19'+year+'/'+month+'/'+day);
            return verifyBirthday('19'+year,month,day,birthday);
        }
        //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
        if(len == '18')
        {
            var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
            var arr_data = card.match(re_eighteen);
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date(year+'/'+month+'/'+day);
            return verifyBirthday(year,month,day,birthday);
        }
        return false;
    };

    //校验日期
    verifyBirthday = function(year,month,day,birthday)
    {
        var now = new Date();
        var now_year = now.getFullYear();
        //年月日是否合理
        if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day)
        {
            //判断年份的范围（3岁到100岁之间)
            var time = now_year - year;
            return (time >= 3 && time <= 100);
        }
        return false;
    };

    //校验位的检测
    checkParity = function(card)
    {
        //15位转18位
        card = changeFivteenToEighteen(card);
        var len = card.length;
        if(len == '18')
        {
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var cardTemp = 0, i, valnum;
            for(i = 0; i < 17; i ++)
            {
                cardTemp += card.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[cardTemp % 11];
            return valnum == card.substr(17, 1);
            return false;
        }
        return false;
    };

    //15位转18位身份证号
    changeFivteenToEighteen = function(card)
    {
        if(card.length == '15')
        {
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var cardTemp = 0, i;
            card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
            for(i = 0; i < 17; i ++)
            {
                cardTemp += card.substr(i, 1) * arrInt[i];
            }
            card += arrCh[cardTemp % 11];
            return card;
        }
        return card;
    };
    //是否为空
    if(card && card != '') {
        //校验长度，类型/省份/生日/检验位的检测
        return isCardNo(card) && checkProvince(card) && checkBirthday(card) &&checkParity(card);
    }else{
        return false;
    }
};
//给带红星号的输入框添加提示不能为空的事件
$(function(){
	$("form").find("label[class='error']").prev().blur(function(){
		if($(this).val()==''){
			$(this).next("label[class='error']").html('不能为空！');
		}else{
			$(this).next("label[class='error']").html('*');
		}
	});
});

function Validator (name){
	this.formName = name;
	this.errMsg = new Array();
	var F=function(fname,elem){
		return document.forms[fname].elements[elem];
	};
	
	this.trim = function(str){
		return str.replace(/^\s*|\s*$/g, "");
	};
	//必填
	this.required = function(element, msg,info){
		var obj = F(this.formName,element);
		if (typeof(obj) == "undefined" || this.trim(obj.value) == ""){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
          return false;
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
            return true;
        }
  	};
	//邮箱
	this.isEmail = function(element, msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var reg = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;
		if (temp == ''){
            if(required){
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }else{
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }
		}else if(temp != ''){
            if (!reg.test(temp)){
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }else{
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }
        }
  	};
	//数字
	this.isNumber = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var reg = /^[\d|\.|,]+$/;
		if (temp == ""){
            if(required){
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
            }
			return !required;
		}else{
            if (reg.test(temp)){
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }else{
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }
        }
  	};
	//选中
	this.isChecked = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var flag = false;
		if (!required && temp == ''){
			return
		}
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				flag=true;
			}
		}
		if (!flag){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
  	};
  	
  	//校友一组checkbox是否有选中
  	this.isCheckboxChecked = function(name,msg,errorInfo){
  		var flag = false;
  		var chks = document.getElementsByName(name);	
  		for(var i=0;i<chks.length;i++){
  			if(chks[i].checked==true){
  				flag=true;
  				break;
  			}
  		}
  		if(!flag){
  			this.addMsg(msg);
  			$("#"+errorInfo).html(msg);
  		}else{
  			$("#"+errorInfo).html("*");
  		}
  	};
  	
	//整数
	this.isInt = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var reg =/^[1-9]\d*$/;
		if (!required && temp == ''){
			return
		}
		if (!reg.test(temp)){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
  	};
	//非零
	this.notZero = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		if (!required && temp == ''){
			return
		}
		if (temp==0){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
  	};
	//邮编
	this.isCode = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var reg =/^(\d){6}$/;
		if (!required && temp == ''){
			return
		}
		if (!reg.test(temp)){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
  	};
	//日期
	this.isDate = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
		if (!required && temp == ''){
			return
		}
		if (!reg.test(temp)){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
  	};
	//电话 
	this.isPhone = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
        var reg=/^((\d{3,4}-)?\d{7,8})$/;     
    	if (!required && temp == ''){
			return
		}
		if (!reg.test(temp)){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
	};
	//手机
	this.isMobile = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
        var reg=/^((13[0-9])|(15[0-9])|(18[0-9]))\d{8}$/;     
    	if (temp == ''){
            if(required){
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }else{
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }
		}else{
            if (!reg.test(temp)){
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }else{
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }
        }
	};
	//身份证号
	this.isIdCard=function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		//var reg1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
		//var reg2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    	if (temp == ''){
            if(required){
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }else{
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }
		}else{
            if (checkIdCard(temp)){
                $("#"+name).find("[name='" + element + "']").next('label').html(info);
                return true;
            }else{
                this.addMsg(msg);
                $("#"+name).find("[name='" + element + "']").next('label').html(msg);
                return false;
            }
        }
	};
	//异步校验
	this.ajaxValidate=function(element,url,msg,info) {
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		if (temp!= "") {
			$.post(url, {element : temp}, function(data) {
				if (data.agrs != 1) {
					$("#"+name).find("[name='" + element + "']").next('label').html(msg);
					this.addMsg(msg);
				}else{
                    $("#"+name).find("[name='" + element + "']").next('label').html(info);
                }
			}, 'json');
		}
	};
	//QQ
	this.isQQ = function(element,msg,required,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
        var reg=/^\d{5,10}$/;     
    	if (!required && temp == ''){
			return
		}
		if (!reg.test(temp)){
		  this.addMsg(msg);
		  $("#"+name).find("[name='" + element + "']").next('label').html(msg);
		}else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
	};
	//验证最大长度
	this.maxLength=function(element,length,msg,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var l=temp.replace(/[^\x00-\xFF]/g,"aa").length;
        if(l>length){
        	this.addMsg(element+length);
        	if(!msg){
        		$("#"+name).find("[name='" + element + "']").next('label').html("最大允许字数"+length+"！");
        	}else{
        		$("#"+name).find("[name='" + element + "']").next('label').html(msg);
        	}
            return false;
        }else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
            return true;
        }
	};
	//长度区间
	this.rangeLength=function(element,minLength,maxLength,msg,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var l=temp.replace(/[^\x00-\xFF]/g,"aa").length;
		if(l<minLength || l>maxLength){
	        this.addMsg(element+"not in"+"["+minLength+","+maxLength+"]");
	        if(!msg){
	        	$("#"+name).find("[name='" + element + "']").next('label').html("长度必须在"+minLength+"和"+maxLength+"之间！");
	        }else{
	        	$("#"+name).find("[name='" + element + "']").next('label').html(msg);
	        }
	    }else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
	};
	//固定长度
	this.length=function(element,length,msg,info){
		var obj = F(this.formName,element);
		var temp = this.trim(obj.value);
		var l=temp.replace(/[^\x00-\xFF]/g,"aa").length;
		if(l!=length){
	        this.addMsg(element+"not"+length);
	        if(!msg){
	        	$("#"+name).find("[name='" + element + "']").next('label').html("长度只能是"+length+"位！");
	        }else{
	        	$("#"+name).find("[name='" + element + "']").next('label').html(msg);
	        }
	    }else{
            $("#"+name).find("[name='" + element + "']").next('label').html(info);
        }
	};
	
	this.addMsg = function(str){
    	this.errMsg.push(str);
	};
	
	this.isPass = function(){
		if (this.errMsg.length > 0){
			var msg = "";
			for (var i = 0; i < this.errMsg.length; i ++ ){
				msg += "" + this.errMsg[i] + "\n";
			}
			return false;
		}else{
			return true;
		}
	};
}