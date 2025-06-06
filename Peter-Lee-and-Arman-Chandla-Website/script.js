let ingredients, dishes,recipies;
function init(){
  $.ajaxSetup({async: false});


      let link = "https://3cd72b26-c6a9-4a24-a740-faa2bdff2416-00-3lb5p1ud61p45.picard.replit.dev:3000/";
      let route= "dishes"
      dishes = $.getJSON(link+route).responseJSON;
      console.log(dishes);
      
      let mainpanel = document.getElementById("mainpanel");
      let build ="";

      let route2= "ingredients"
      ingredients = $.getJSON(link+route2).responseJSON;
      console.log(ingredients);

     let route3= "recipies"
     recipies = $.getJSON(link+route3).responseJSON;
     console.log(recipies);

 
      

    
}
