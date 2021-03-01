const entities = {
  playerParty: {
    characters: [
      {
        name: "Lāčplēsis",
        hp: 80,
        maxhp: 100,
        battleOptions: ["Zobena Cirtiens", "Gulēt", "Lāča Dūre", "Lāča Spēks"],
        battleOptionFunction: ["-1", "1", "0", "-1"]
      },
      {
        name: "Spīdola",
        hp: 60,
        maxhp: 60,
        battleOptions: ["Apburtais Pieskāriens", "Saules Dūriens", "Gara Cēlums", "Atmostas Varoņi"],
        battleOptionFunction: ["0", "-1", "2", "2"]
      },
    ]
  },
  enemyParty: {
    characters: [
      {
        name: "Baigais",
        hp: 100,
        maxhp: 100,
        battleOptions: ["Attack", "Magic Attack"]
      },
      {
        name: "Fujaks",
        hp: 0,
        maxhp: 100,
        battleOptions: ["Attack", "Magic Attack"]
      },
    ]
  }
};

///// json string created /////
var json_string = JSON.stringify(entities);
let json = JSON.parse(json_string)
////

function create_battle(json, character, friendly, member){
  let container = document.createElement("div");
  container.classList.add("container");

  if (!friendly) {
    container.classList.add("enemy");
  }

  container.id = character.name + "_container";
  
  let info = document.createElement("div");
  info.classList.add("info");

  let name = document.createElement("div");
  name.id = character.name + "_name";
  name.innerHTML = character.name;
  
  let hp = document.createElement("div");
  hp.id = character.name + "_hp";
  hp.innerHTML = character.hp + "/" + character.maxhp + "HP";
  
  if (friendly) {
    container.style.bottom = 30 + member * 5 + "%";
    container.style.left = 1 + member * 11 + "%";
    
    if (json["playerParty"].characters[member].hp > 0) {
      container.style.background = "url(" + json["playerParty"].characters[member].name + ".png) bottom center /contain no-repeat";
      
    }
    ////need to create a ded png for main characters
    /*else {
      container.style.background = "url("+ character.name +"_ded.png) bottom center /contain no-repeat";
    }*/

    name.style.color = "yellow";
    hp.style.color = "yellow";

  } 
  else {
    container.style.bottom = 30 + member * 5 + "%";
    container.style.right = 1 + member * 11 + "%";
    
    if (json["enemyParty"].characters[member].hp > 0) {
      container.style.background = "url(TautasVaronis.png) bottom center /contain no-repeat";
    } else {
      container.style.background = "url(ded.png) bottom center /contain no-repeat";
    }

    name.style.color = "#051c40";
    name.style.textShadow = "1px 0 black, 0 1px black, 1px 0 black, 0 1px black";
    hp.style.color = "#051c40";
    hp.style.textShadow = "1px 0 black, 0 1px black, 1px 0 black, 0 1px black";

  }
  
  info.appendChild(name);
  info.appendChild(hp);
  container.appendChild(info);

  document.body.appendChild(container);
}

/////animate/////
function animate(daritajs, daramais, hpChange) {
  let daritajs_cont = document.getElementById(daritajs + "_container");
  let daramais_cont = document.getElementById(daramais + "_container");
  let hp_me = document.getElementById(daritajs + "_hp");
  let hp_notme = document.getElementById(daramais + "_hp");

  //let hp_me_int = parseInt(hp_notme.innerHTML.split("/")[0]);
  //let hp_notme_int = parseInt(hp_notme.innerHTML.split("/")[0]);

  var left_previous_left = daritajs_cont.style.left;
  var left_previous_bottom = daritajs_cont.style.bottom;

  var right_previous_right = daramais_cont.style.right;
   right_previous_bottom = daramais_cont.style.bottom;


  if (hpChange < 0) {
    hp_notme.innerHTML = "60" + "/" + "100" + "HP";
    daritajs_cont.style.left = "25%";
    daritajs_cont.style.bottom = "25%";

    daramais_cont.style.right = "25%";
    daramais_cont.style.bottom = "25%";
    
    daritajs_cont.style.animation = "attack 2s"

    setTimeout(() =>{
    daramais_cont.style.animation = "shake 0.5s infinite";
    }, 600);

    setTimeout(() => {
      daritajs_cont.style.left = left_previous_left;
      daritajs_cont.style.bottom = left_previous_bottom;

      daramais_cont.style.right = right_previous_right;
      daramais_cont.style.bottom = right_previous_bottom;

      daritajs_cont.style.animation = "none";
      daramais_cont.style.animation = "none";
    }, 3000);
  }

  else if (hpChange > 0) {
    hp_me.innerHTML = "100" + "/" + "100" + "HP";
    daramais_cont.style.animation = "heal 4s"

    setTimeout(() => {
      daramais_cont.style.animation = "none";
    }, 4000);
  } 

  else {
    daritajs_cont.style.animation = "shake 0.5s infinite";

    setTimeout(() => {
      daritajs_cont.style.animation = "none";
    }, 1000);
  }
}

function takeTurn(varonis){
  let daritajs = document.getElementById(varonis + "_container");

  const previous_loc_int = parseInt(daritajs.style.bottom);
  daritajs.style.bottom = (previous_loc_int + 8).toString() + "%";


  let sliktie = json.enemyParty.characters;
  let sliktie_html = document.getElementsByClassName("enemy");

  let labie = json.playerParty.characters;
  let labie_html = document.getElementsByClassName("container");

  let battle_options = document.getElementById("battleOptions")
  let status = document.getElementById("statusText")
  
  let selectAttackMethod = document.getElementById("selectAttackMethod");
  selectAttackMethod.style.display = "block";
  
  let options = [];
  let sliktie_list = [];
  let labie_list = [];

  json.playerParty.characters.forEach(character => {
    if(character.name == varonis){
    character.battleOptions.forEach(battleOption => {
        let option = document.createElement("div");
        option.classList.add("battleOptionButton");
        option.innerHTML = battleOption;
  
        options.push(option);
        battle_options.appendChild(option);

        option.addEventListener("click", (ii) => {

          options.forEach(option => {
            battle_options.removeChild(option);
          });
          selectAttackMethod.style.display = "none";
          status.innerHTML = "Izvēlies varoni!";
          
          let hp_change = character.battleOptionFunction[character.battleOptions.indexOf(battleOption)];

          if (hp_change == -1){

            sliktie.forEach(sliktais => {
              if (sliktais.hp > 0){
                sliktie_list.push(sliktie_html[sliktie.indexOf(sliktais)])
                sliktie_html[sliktie.indexOf(sliktais)].style.cursor = "pointer";
                sliktie_html[sliktie.indexOf(sliktais)].addEventListener("click", (j) => {
                  animate(varonis, sliktais.name, -20)
                  
                  setTimeout(() => {
                    daritajs.style.bottom = previous_loc_int.toString() + "%";
                    sliktie_list.forEach(remove => {
                      remove.style.cursor = "initial";
                      let replacement = remove.cloneNode(true);
                      remove.parentNode.replaceChild(replacement, remove)
                    });
                  }, 3001);
                });
              }
            });
          }

          else if (hp_change == 0){

            sliktie.forEach(sliktais => {
              if (sliktais.hp > 0){
                sliktie_list.push(sliktie_html[sliktie.indexOf(sliktais)])
                sliktie_html[sliktie.indexOf(sliktais)].style.cursor = "pointer";
                sliktie_html[sliktie.indexOf(sliktais)].addEventListener("click", (j) => {
                  animate(varonis, sliktais.name, 0)

                  setTimeout(() => {
                    daritajs.style.bottom = previous_loc_int.toString() + "%";
                    sliktie_list.forEach(remove => {
                      remove.style.cursor = "initial";
                      let replacement = remove.cloneNode(true);
                      remove.parentNode.replaceChild(replacement, remove)
                    });
                  }, 1000);
                });
              }
            });
          }
            
          else if (hp_change == 1){
            animate(varonis, varonis, 20)
            setTimeout(() => {
              daritajs.style.bottom = previous_loc_int.toString() + "%";
            }, 4001);
          }

          else if (hp_change == 2){
            labie.forEach(labais => {
              if (labais.hp >= 0){
                labie_list.push(labie_html[labie.indexOf(labais)])
                labie_html[labie.indexOf(labais)].style.cursor = "pointer";
                labie_html[labie.indexOf(labais)].addEventListener("click", (j) => {
                  animate(varonis, labais.name, 20)

                  setTimeout(() => {
                    daritajs.style.bottom = previous_loc_int.toString() + "%";
                    labie_list.forEach(remove => {
                      remove.style.cursor = "initial";
                      let replacement = remove.cloneNode(true);
                      remove.parentNode.replaceChild(replacement, remove)
                    });
                  }, 4001);
                });
              }
            });
          }
        });
      });
    }
    //choose_character()
  });
};

function choose_character(){
  
  let labie_list = [];
  let labie = json.playerParty.characters;
  let labie_html = document.getElementsByClassName("container");

  labie.forEach(labais => {
    
    labie_list.push(labie_html[labie.indexOf(labais)])
    labie_html[labie.indexOf(labais)].style.cursor = "pointer";
    labie_html[labie.indexOf(labais)].addEventListener("click", (j) => {
      labie_list.forEach(remove => {
        remove.style.cursor = "initial";
        let replacement = remove.cloneNode(true);
        remove.parentNode.replaceChild(replacement, remove)
      });

      takeTurn(labais.name);
    });

  });
}

for (let i = 0; i < json.playerParty.characters.length; i++) {
  create_battle(json, json.playerParty.characters[i], true, i);
  console.log("url(" + json.playerParty.characters[i].name + ".png) bottom center /contain no-repeat")
}

for (let i = 0; i < json.enemyParty.characters.length; i++) {
  create_battle(json, json.enemyParty.characters[i], false, i);
}
choose_character()

//takeTurn("Lāčplēsis");
//takeTurn("Spīdola");
//animate("Lāčplēsis", "Baigais", -20)
//animate("Lāčplēsis", "Baigais", 0)
//animate("Spīdola", "Lāčplēsis", 20)

