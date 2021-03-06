function viewChampionData(){
    let innerHtml;
    let i;
    let initTraitsDesc = document.getElementById("initTraitsDesc").value;
    let initTraitsTier = document.getElementById("initTraitsTier").value;
    let initTraitsTierDesc = document.getElementById("initTraitsTierDesc").value;
    let initTraitsNumUnits = document.getElementById("initTraitsNumUnits").value.split("$");
    let initTraitsNumUnitsKr = document.getElementById("initTraitsNumUnitsKr").value.split("$");
    let initUsedAnotherUnit = document.getElementById("initUsedAnotherUnit").value.split("|");

    innerHtml = "<img src='/img/tft/synergy/";
    innerHtml += document.getElementById("initTraitsName").value.substring(5,document.getElementById("initTraitsName").value.length);
    innerHtml += ".png' className='synergyContentsImage' alt=''>";
    innerHtml += "<span class='synergyContentsName'>";
    innerHtml += document.getElementById("initTraitsNameKr").value;
    innerHtml += "</span>"
    document.getElementById("traitsName").innerHTML = innerHtml;

    innerHtml = "1등 지분율   ";
    innerHtml += document.getElementById("initWinRate").value;
    innerHtml += "%";
    document.getElementById("winRate").innerHTML = innerHtml;

    if(initTraitsDesc !== ""){
        innerHtml = initTraitsDesc.replaceAll("$","<br>");
    }
    if(initTraitsTier !== ""){
        innerHtml += initTraitsTier.replaceAll("$","<br>");
    }
    if(initTraitsTierDesc !== ""){
        innerHtml += "<br><br>" + initTraitsTierDesc.replaceAll("$","<br>");
    }
    document.getElementById("traitsDesc").innerHTML = innerHtml;

    innerHtml = "";
    for(i=0; i<initTraitsNumUnits.length; i++) {
        innerHtml += "<div class='contentsListName'>";
        innerHtml += "<img src='/img/tft/champion/";
        innerHtml += initTraitsNumUnits[i];
        innerHtml += ".png' class='synergyContentsImage' alt=''>";
        innerHtml += initTraitsNumUnitsKr[i];
        innerHtml += "</div>";
    }
    document.getElementById("traitsNumUnits").innerHTML = innerHtml;

    innerHtml = "";
    for(i=0; i<initUsedAnotherUnit.length; i++) {
        innerHtml += "<div class='contentsListName'>";
        innerHtml += "<img src='/img/tft/champion/";
        innerHtml += initUsedAnotherUnit[i].substring(5,initUsedAnotherUnit[i].length);
        innerHtml += ".png' class='synergyContentsImage' alt=''>";
        innerHtml += initUsedAnotherUnit[i].substring(5,initUsedAnotherUnit[i].length);
        innerHtml += "</div>";
    }
    document.getElementById("usedAnotherUnit").innerHTML = innerHtml;

    let initUsedAugments = document.getElementById("initUsedAugments").value.split("|");
    let augmentsTier = document.getElementById("augmentTier").value.split("|");
    innerHtml = "";
    for(i=0; i<initUsedAugments.length; i++) {
        innerHtml += "<div class='contentsListName'>";
        innerHtml += "<img src='/img/tft/augment/tier"+augmentsTier[i]+"/";
        innerHtml += initUsedAugments[i].substring(13,initUsedAugments[i].value);
        innerHtml += ".png' class='synergyContentsImage' alt=''>";
        innerHtml += initUsedAugments[i].substring(13,initUsedAugments[i].value);
        innerHtml += "</div>";
    }
    document.getElementById("usedAugments").innerHTML = innerHtml;
    let button = document.getElementById(document.getElementById("initTraitsName").value);
    button.checked = true;
}

let overlapCheck = "Set6_Enchanter";
function getSynergyData ( synergyName ){
    if(overlapCheck === synergyName){
    }else{
        overlapCheck = synergyName;
        const xhr = new XMLHttpRequest();
        const method = "GET";
        const url = "/tft/synergyList/" + synergyName;
        xhr.open(method, url);
        xhr.send();
        xhr.onload = function() {
            if (xhr.status >= 200 && xhr.status < 300) {
                const synergyData = JSON.parse(xhr.response);
                document.getElementById("initTraitsName").value = synergyData.initSynergy.traitsName;
                document.getElementById("initTraitsNameKr").value = synergyData.initSynergy.traitsNameKr;
                document.getElementById("initTraitsDesc").value = synergyData.initSynergy.traitsDesc;
                document.getElementById("initTraitsTier").value = synergyData.initSynergy.traitsTier;
                document.getElementById("initTraitsTierDesc").value = synergyData.initSynergy.traitsTierDesc;
                document.getElementById("initTraitsNumUnits").value = synergyData.initSynergy.traitsNumUnits;
                document.getElementById("initTraitsNumUnitsKr").value = synergyData.initSynergy.traitsNumUnitsKr;
                document.getElementById("initWinRate").value = synergyData.initSynergy.winRate;
                document.getElementById("initUsedAnotherUnit").value = synergyData.initSynergy.usedAnotherUnit;
                document.getElementById("initUsedAugments").value = synergyData.initSynergy.usedAugments;
                document.getElementById("augmentTier").value = synergyData.augmentTier;
                viewChampionData();
            } else {
                console.log('failed');
            }
        }
    }
}
viewChampionData();