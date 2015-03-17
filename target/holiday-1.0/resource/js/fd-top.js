$(window).scroll(function (e) {
	
	var displayforTopHeight=900;

	if($(document).scrollTop()>displayforTopHeight)
	{
		window.document.getElementById("bdg").style.display="block";
	}else
	{
		window.document.getElementById("bdg").style.display="none";
	}
	
});

