const baseurl = "http://localhost:11111/api/recipes";

// CREATE LIST OF INGREDIENTS

function fillInDetails(data) {
	let html = `
		<h1>${data.title} </h1>
		<h2>${data.description} </h2>
		<div>Minutes: ${data.minutes} </div>
		<h3>Ingredients</h3>

	`;
	for (let ingredient of data.ingredients) {  // THIS WORKS
		html += `
			<div>
				<div>
					<b> ${ingredient.foodItem} </b>
					<div> ${ingredient.unitOfMeasure} </div>
					<div> ${ingredient.quantity}</div>	
				</div>
				<form class="delete-ins-ing-form" method="post" action="/api/recipes/${data.id}/ingredients/${ingredient.id}">
	                <button>Delete</button>
				</form>
			</div>
		`;
	}
	html += `<h3>Instructions</h3>`

	for (let instruction of data.instructions) { // THIS LOOPS AND PRINTS INSTRUCTIONS
		html += `
			<div>
			<div>
				<div> ${instruction.step}</div>	
			</div>
			<form class="delete-ins-ing-form" method="post" action="/api/recipes/${data.id}/instructions/${instruction.id}">
                <button>Delete</button>
			</form>
			</div>
		`;
	}
	html += ` 
	<form id="create-ingredient-form" method="post" action="/api/recipes/${data.id}/ingredients">
				<input name="foodItem" id="foodItem" placeholder="Food item">
				<br>
				<input name="unitOfMeasure" id="unitOfMeasure" placeholder="Unit of Measure">
				<br>
				<input name="quantity" id="quantity" placeholder="Quantity">
				</br>
				<button>Add an ingredient!</button>
	</form>
	<form id="create-instruction-form" method="post" action="/api/recipes/${data.id}/instructions">
				<input name="step" id="step" placeholder="Step">
				<br>
				<button>Add an instruction!</button>
	</form>
	`; // THIS WORKS
	$('#recipe-detail').html(html);
}

// CREATES LIST ELEMENTS OF RECIPE WITH DELETE BUTTON
function createListElement(recipe) {
    $('<li></li>')
        .html(`
            <a href="#" data-recipe-id="${recipe.id}">
            ${recipe.title}
            </a>
            <form class="delete-recipe-form" method="post" action="/api/recipes/${recipe.id}">
                <button>Delete this recipe</button>
            </form>
        `)
        .appendTo($('#recipe-list'));
}


// ACTUALLY REMOVES THE ELEMENT WHEN YOU CLICK THE DELETE BUTTON

$(document).on('submit', '.delete-recipe-form', function (e) {
    e.preventDefault();
    
    $.ajax(this.action, { type: 'DELETE' })
        .done(() => {
            $(this)
                .closest('li')
                .remove();
        })
        .fail(error => console.error(error));
});

// DELETE INGREDIENTS AND INSTRUCTIONS

$(document).on('submit', '.delete-ins-ing-form', function (e) {
    e.preventDefault();
    
    $.ajax(this.action, { type: 'DELETE' })
        .done(() => {
            $(this)
                .closest('div')
                .remove();
        })
        .fail(error => console.error(error));
});

// CREATE INGREDIENT FORM

$(document).on('submit', '#create-ingredient-form', function (e) {
	e.preventDefault();
	
	let payload = {
			foodItem: $('#foodItem').val(),
			unitOfMeasure: $('#unitOfMeasure').val(),
			quantity: $('#quantity').val()	
		};
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(payload),
			contentType: 'application/json'
		};
	
	$.ajax(this.action, ajaxOptions)
	.done(function (data) {
		fillInDetails(data);
	})
	.fail(error => console.error(error));
	
});


// CREATE INSTRUCTION FORM

$(document).on('submit', '#create-instruction-form', function (e) {
	e.preventDefault();
	
	let payload = {
			step: $('#step').val(),
		};
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(payload),
			contentType: 'application/json'
		};
	
	$.ajax(this.action, ajaxOptions)
	.done(function (data) {
		fillInDetails(data);
	})
	.fail(error => console.error(error));
	
});

// CREATE RECIPE FORM

$('#create-recipe-form').on('submit', function (e) {
	e.preventDefault();
	
	let payload = {
		title: $('#title').val(),
		description: $('#description').val(),
		minutes: $('#minutes').val()	
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (recipe) {
			createListElement(recipe);
		})
	.fail(error => console.error(error));
});

// LOAD DETAILS OF RECIPE (description, minutes, ingredients, steps)

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