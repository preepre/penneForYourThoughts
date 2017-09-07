const baseurl = "http://localhost:11111/api/recipes";

$(document).on('click', 'a[data-recipe-id]', function (e){
	e.preventDefault();
	
	const recipeId = $(this).data('recipeId');
	
	$.getJSON(baseurl + '/' + recipeId, function (data) {
		$('#recipe-detail')
		.html(`
				<h1>${data.title} </h1>
				<h2>${data.description} </h2>
				<div>Minutes: ${data.minutes} </div>
		`)
	});
	
});

$.getJSON(baseurl, function (data) {
	if (data.length) {
		for(let recipe of data){
			$('<li></li>')
			.html('<a href="#" data-recipe-id="' + recipe.id + '">' + 
					recipe.title + '</a>')
			.appendTo($('#recipe-list'));
		}
		
		
	} else {
		$('<li></li>')
		.css('color', 'red')
		.html('You have no data.')
		.appendTo($('#recipe-list'));
	}
});