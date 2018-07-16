function up(max,pos) {
	nId="myNumber"+pos;
	dp="dp"+pos;
	if(document.getElementById(nId).value < parseInt(max)){
		 document.getElementById(nId).value = parseInt(document.getElementById(nId).value) + 1;
		 result=document.getElementById("dish_sun").innerHTML*1 + document.getElementById(dp).innerHTML*1;
	     document.getElementById("dish_sun").innerHTML = Math.round(result * 100) / 100;
	}else{
        document.getElementById(nId).value = max;
    }    
	
}
function down(min,pos) {
	nId="myNumber"+pos;
	dp="dp"+pos;
	if(document.getElementById(nId).value > parseInt(min)){
		document.getElementById(nId).value = parseInt(document.getElementById(nId).value) - 1;
		result=document.getElementById("dish_sun").innerHTML*1 - document.getElementById(dp).innerHTML*1;
		document.getElementById("dish_sun").innerHTML = Math.round(result * 100) / 100;
	}else{
		document.getElementById(nId).value = min;
	}
}
