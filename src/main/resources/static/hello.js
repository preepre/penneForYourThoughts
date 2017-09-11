const baseurl = "http://localhost:11111/api/recipes";


function fillInDetails(data){
	
	let html = `
		<h1>${data.title} </h1>
		<h2>${data.description} </h2>
		<div>Minutes: ${data.minutes} </div>
		`;
	
	for(let ingredients of data.ingredients) {  
		html += `
		<div>
			<b>${ingredients.foodItem}</b>
			<div>${ingredients.quantity}</div>
			<div>${ingredients.unitOfMeasure}</div>
		</div>
		
		<form class="delete-ingredients-form" method="post" action="/api/recipes/${data.id}/ingredients/${ingredients.id}">
			<button>Delete</button>
		</form>
		<br>
		`;
	}
	
	
	html += `
	<form id="create-ingredients-form" method="post" action="/api/recipes/${data.id}/ingredients">
	<input required name="foodItem" id="foodItem" placeholder="food item">
	<br>
	<input name="quantity" id="quantity" placeholder="quantity">
	<br>
	<input required name="unitOfMeasure" id="unitOfMeasure" placeholder="unit Of Measure">
	<br>
	<button>Add ingredient</button>
	</form>
	<br>

	`;
	

	for(let instructions of data.instructions) {  
		html += `
		<div>
			<div>${instructions.step}</div>
		</div>
		
		<form class="delete-instructions-form" method="post" action="/api/recipes/${data.id}/instructions/${instructions.id}">
			<button>Delete</button>
		</form>
		<br>
		`;
	}
	
	html += `
	<br>
	<form id="create-instructions-form" method="post" action="/api/recipes/${data.id}/instructions">
	<input required name="step" id="step" placeholder="step">
	<br>
	<button>Add instruction</button>
	</form>
	
	`;
	
	$('#recipe-detail').html(html);	
	
	
}


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

$(document).on('submit', '.delete-ingredients-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, {type: 'DELETE' })
		.done(() => {
			$(this)
			
			.remove();
		})
		.fail(error => console.error(error));
	
});

$(document).on('submit', '.delete-instructions-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, {type: 'DELETE' })
		.done(() => {
			$(this)
			
			.remove();
		})
		.fail(error => console.error(error));
	
});


$('#create-recipe-form').on('submit', function (e) {
	e.preventDefault();	

	let recipeLoad = {
			title: $('#title').val(),
			description: $('#description').val(),
			minutes: $('#minutes').val(),
			ingredients: [],
			instructions: []
			
			
	};
	
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(recipeLoad),
			contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (data){
			fillInDetails(data);
			
		})
		
		.fail(error => console.error(error));
		
	
});

$(document).on('submit', '#create-ingredients-form', function(e){
	
	e.preventDefault();
	
	let recipeLoad = {
			foodItem: $('#foodItem').val(),
			quantity: $('#quantity').val(),
			unitOfMeasure: $('#unitOfMeasure').val()
	};
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(recipeLoad),
			contentType: 'application/json'
			
	};
	
	$.ajax(this.action, ajaxOptions)
	.done(function (data){
		fillInDetails(data);
		
	})
	
	.fail(error => console.error(error));

	
});


$(document).on('submit', '#create-instructions-form', function(e){
	
	e.preventDefault();
	
	let recipeLoad = {
			step: $('#step').val()
	};
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(recipeLoad),
			contentType: 'application/json'
			
	};
	
	$.ajax(this.action, ajaxOptions)
	.done(function (data){
		fillInDetails(data);
		
	})
	
	.fail(error => console.error(error));

	
});


$(document).on('click', 'a[data-recipe-id]', function (e){
	e.preventDefault();
	
	const recipeId = $(this).data('recipeId');
	
	$.getJSON(baseurl + '/' + recipeId, function (data) {
		
		fillInDetails(data);								
		
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
