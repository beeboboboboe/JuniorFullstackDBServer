let data,ingredients;
function init(){
  $.ajaxSetup({async: false});

  let link = "https://3cd72b26-c6a9-4a24-a740-faa2bdff2416-00-3lb5p1ud61p45.picard.replit.dev:3000";
  let route= "/ingredients"
  ingredients = $.getJSON(link+route).responseJSON;

  generateCards(ingredients);


}


function generateCards(ingredients){

  let mainpanel = document.getElementById("output");
  let build ="";


 for (let i = 0; i < ingredients.length; i++) {
      let ingredient = ingredients[i]
      build += `<div class="card" >`
      build += `<h3> Ingredients : ${ingredient.IngredientId}</h3>`;
      build += `<h3> Seasoning : ${ingredient.Seasoning}<h3>`;
      build += `<h3> Seasoning : ${ingredient.seasoning2}<h3>`;
      build += `<h3> Seasoning : ${ingredient.seasoning3}<h3>`;
      build += `<h3> Dairy : ${ingredient.Dairy}<h3>`;
      build += `<h3> Herbs : ${ingredient.Herbs}<h3>`;  
      build += `<h3> Proteins : ${ingredient.Proteins}<h3>`;
      build += `<hr>`;
      build += `</div>`;
    }

   mainpanel.innerHTML = build;
}

