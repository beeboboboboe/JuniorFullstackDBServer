let data,recipies;
function init(){
  $.ajaxSetup({async: false});

  let link = "https://3cd72b26-c6a9-4a24-a740-faa2bdff2416-00-3lb5p1ud61p45.picard.replit.dev:3000";
  let route= "/recipies"
  recipies = $.getJSON(link+route).responseJSON;

  generateCards(recipies);


}

function generateCards(recipies){

  let mainpanel = document.getElementById("output");
  let build ="";


 for (let i = 0; i < recipies.length; i++) {
      let recipie = recipies[i]
      build += `<div class="card" >`
      build += `<h3> Recipe : ${recipie.DishId}</h3>`;
      build += `<h3> Time : ${recipie.Time}<h3>`;
      build += `<h3> Tools : ${recipie.Tools}<h3>`;
      build += `<h3> Decsription : ${recipie.description}<h3>`;
      build += `<hr>`;
      build += `</div>`;
    }

   mainpanel.innerHTML = build;


}