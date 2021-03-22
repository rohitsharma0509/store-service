$(document).ready(function(){
	$('.double').keypress(function(event) {
		var value = $(this).val(); 
		var regExp ="^\\d+(\\.\\d+)?$";
		return value.match(regExp); 
	});
});