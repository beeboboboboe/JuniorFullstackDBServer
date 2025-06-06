let data,dishes;
function init(){
  $.ajaxSetup({async: false});

  let link = "https://3cd72b26-c6a9-4a24-a740-faa2bdff2416-00-3lb5p1ud61p45.picard.replit.dev:3000";
  let route= "/dishes"
  dishes = $.getJSON(link+route).responseJSON;

  generateCards(dishes);


}

function generateCards(dishes){

  let mainpanel = document.getElementById("output");
  let build ="";

  


 for (let i = 0; i < dishes.length; i++) {
      let dish = dishes[i]
      build += `<div class="card" >`
      build += `<h3> Dishes : ${dish.DishId}</h3>`;
      build += `<h3> Dish Name : ${dish.Name}<h3>`;
      build += `<h3> Dish Genre : ${dish.Genre}<h3>`;
      build += `<h3> Dish Inventor : ${dish.Inventor}<h3>`;
      build += `<hr>`;
      build += `</div>`;
    }

   mainpanel.innerHTML = build;
}