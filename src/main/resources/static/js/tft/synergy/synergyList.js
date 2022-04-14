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
        innerHtml += initUsedAnotherUnit[i].substring(5,document.getElementById("initTraitsName").value.length);
        innerHtml += ".png' class='synergyContentsImage' alt=''>";
        innerHtml += initUsedAnotherUnit[i].substring(5,document.getElementById("initTraitsName").value.length);
        innerHtml += "</div>";
    }
    document.getElementById("usedAnotherUnit").innerHTML = innerHtml;

    let initUsedAugments = document.getElementById("initUsedAugments").value.split("|");
    let augmentsTier = document.getElementById("augmentTier").value.split("|");
    innerHtml = "";
    for(i=0; i<initUsedAugments.length; i++) {
        innerHtml += "<div class='contentsListName'>";
        innerHtml += "<img src='/img/tft/augment/"+augmentsTier[i]+"/";
        innerHtml += initUsedAugments[i].substring(13,initUsedAugments[i].value);
        innerHtml += ".png' class='synergyContentsImage' alt=''>";
        innerHtml += initUsedAugments[i].substring(13,initUsedAugments[i].value);
        innerHtml += "</div>";
    }
    document.getElementById("usedAugments").innerHTML = innerHtml;
    let button = document.getElementById("button_"+ document.getElementById("initTraitsName").value);
    button.checked = true;
}
viewChampionData();