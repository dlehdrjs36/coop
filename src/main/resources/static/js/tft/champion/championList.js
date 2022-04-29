const championsJson = JSON.parse(document.getElementById("championListData").value).championList;
const synergyListJson = JSON.parse(document.getElementById("synergyListData").value).synergyList;

function championListInit(){
    let innerHtml = "";
    for( let i=0; i<championNameList.length; i++ ){
        innerHtml += '<div class="championList championListName" onclick=viewChampionData(';
        innerHtml += '"' + championNameList[i] + '",';
        innerHtml += '"' + championNameKrList[i] + '"';
        innerHtml += ')><img src="/img/tft/champion/';
        innerHtml += championNameList[i];
        innerHtml += '.png" class="championListImage">';
        innerHtml += championNameKrList[i];
        innerHtml += '</div>';
    }
    document.getElementById("championList").innerHTML += innerHtml;

    let searchChampionName = document.getElementById("championName").value;
    if(searchChampionName !== ""){
        for( let i=0; i<championNameList.length; i++){
            if(searchChampionName === championNameList[i]){
                viewChampionData(championNameList[i], championNameKrList[i]);
            }
        }
    }
}
function viewChampionData( championName,  championNameKr ){
    viewChampionImage( championName,  championNameKr );
    viewChampionAbility( championName );
}

function viewChampionImage( championName,  championNameKr ){
    document.getElementById("championViewerHeader").innerHTML = "<div class='championBackground bg-black' id='championBg'><span class='championName'>" + championNameKr + "</span></div>";
    document.getElementById("championBg").style.backgroundImage = "url('/img/tft/champion/centered/" + championName + "BgImg.png')";
}

function viewChampionAbility( championName ){
    let innerHtml = "";
    for(let i = 0; i < championsJson.length; i++){
        if(championsJson[i].name.indexOf(championName) !== -1){
            if( championName === championsJson[i].name ){
                innerHtml += '<table class="table table-light align-items-center border-top border-start border-end">';
                innerHtml += '<tbody>';
                innerHtml += '<th class="col-2">능력치</th><th class="col-2"></th><th class="col-1">스킬</th><th class="col-4"></th>';
                innerHtml += '<tr>';
                innerHtml += '<td>체력</td>';
                innerHtml += '<td>'+ championsJson[i].health +'</td>';
                innerHtml += '<td rowspan="6" class="border-start border-end">';
                innerHtml += '<div>' + championsJson[i].skill + '</div>';
                innerHtml += '<img src="/img/tft/champion/skill/' + championsJson[i].name + 'Skill.png" alt="">';
                innerHtml += '</td>';
                innerHtml += '<td rowspan="6" colspan="2">';
                innerHtml += championsJson[i].skillDesc;
                innerHtml += '<br><br>';
                for(let j=0; j<championsJson[i].skillAbility.length; j++){
                    innerHtml += championsJson[i].skillAbility[j];
                    innerHtml += '<br>';
                }
                innerHtml += '</td>';
                innerHtml += '</tr>';
                innerHtml += '<tr>';
                innerHtml += '<td>마나</td>';
                innerHtml += '<td>' + championsJson[i].mana + '</td>';
                innerHtml += '</tr>';
                innerHtml += '<tr>';
                innerHtml += '<td>공격력</td>';
                innerHtml += '<td>' + championsJson[i].ad + '</td>';
                innerHtml += '</tr>';
                innerHtml += '<tr>';
                innerHtml += '<td>물리 방어력/마법 저항력</td>';
                innerHtml += '<td>' + championsJson[i].defenses + '</td>';
                innerHtml += '</tr>';
                innerHtml += '<tr>';
                innerHtml += '<td>공격속도</td>';
                innerHtml += '<td>' + championsJson[i].as + '</td>';
                innerHtml += '</tr>';
                innerHtml += '<td>사거리</td>';
                innerHtml += '<td>' + championsJson[i].crossroad + '</td>';
                innerHtml += '</tr>';
                document.getElementById("championAbility").innerHTML = innerHtml;
                viewSynergyListDesc(championsJson[i].augment);
            }
        }
    }
}

function viewSynergyListDesc( synergy ){
    let innerHtml = "";
    innerHtml += '<div class="synergyHeader">시너지</div>';
    for(let i=0; i<synergy.length; i++){
        for(let j=0; j<synergyListJson.length; j++){
            if(synergyListJson[j].name.indexOf(synergy[i]) !== -1){
                if( synergy[i] === synergyListJson[j].name) {
                    innerHtml += '<span class="synergy bg-light"><div class="contentsPadding"><img src="/img/tft/synergy/' + synergyListJson[j].name + '.png" alt=""><span class="synergyName">' + synergyListJson[j].nameKr + '</span></div></span>';
                    innerHtml += '<div class="contentsPadding sameSynergyChampionList">';
                    for (let k = 0; k < synergyListJson[j].championName.length; k++) {
                        innerHtml += '<div class="championList championListName" onclick=viewChampionData("' + synergyListJson[j].championName[k] + '","' + synergyListJson[j].championNameKr[k].replace(" ","") + '")><img src="/img/tft/champion/' + synergyListJson[j].championName[k] + '.png" class="championListImage" alt="">' + synergyListJson[j].championNameKr[k] + '</div>';
                    }
                    innerHtml += '</div>';
                    innerHtml += '<div class="contentsPadding synergyDesc bg-light">';
                    for(let k = 0; k < synergyListJson[j].desc.length; k++){
                        innerHtml += synergyListJson[j].desc[k];
                        innerHtml += '<br><br>';
                    }
                    for(let k = 0; k < synergyListJson[j].synergyAbility.length; k++){
                        innerHtml += synergyListJson[j].synergyAbility[k];
                        innerHtml += '<br>';
                    }
                    innerHtml += '</div>';
                }
            }
        }
    }
    document.getElementById("synergyDesc").innerHTML = innerHtml;
}

championListInit();