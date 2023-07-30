//사이드바
function myMenucheck(menu){
	$('.myMenu').each(function(idx,item){
		if($(item).text() == menu){
			$(item).css('color', '#95D083')
			$(item).css('font-weight', '900')
		}
	})
}