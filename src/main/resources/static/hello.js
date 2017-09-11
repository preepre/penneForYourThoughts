const baseurl = "http://localhost:11111/api/recipes";

function createListElement(recipe) {
	
	$('<li></li>')
	
	.html(`
	
	<a href="#" data-recipe-id="${recipe.id}">
			${recipe.title}, ${recipe.description}, 
			${recipe.minutes}
	</a>
	<form class="delete-recipe-form" method="post" action="/api/recipes/${recipe.id}">
			<button>Delete</button>
	</form>
	
	`)
	.appendTo($('#recipe-list'));
	
}

$(document).on('submit', '.delete-recipe-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, {type: 'DELETE' })
		.done(() => {
			$(this)
			.closest('li')
			.remove();
		})
		.fail(error => console.error(error));
	
	
});

$('#create-recipe-form').on('submit', function (e) {
	e.preventDefault();	

	let recipeLoad = {
			title: $('#title').val(),
			description: $('#description').val(),
			minutes: $('#minutes').val()
			
	};
	
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(recipeLoad),
			contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (recipe){
			createListElement(recipe);
			
		})
		
		.fail(error => console.error(error));
		
	
});

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
			createListElement(recipe);
		}
		
		
	} else {
		$('<li></li>')
		.css('color', 'red')
		.html('You have no data.')
		.appendTo($('#recipe-list'));
	}
});