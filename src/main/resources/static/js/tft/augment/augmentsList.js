const augmentsNameTier1 = [["Ancientarchives"],["Arcanenullifier"],["Arcanistheart"],["Ardentcenser"],["Ascension"],["Assassinheart"],["Backfoot"],["Battlemage"],["Bluebattery"],["Bodyguardheart"],["Bruiserheart"],["Builtdifferent"],["Calculatedloss"],["Celestialblessing"],["Challengerheart"],["Chemicaloverlord"],["Chemtechheart"],["ClockworkHeart"],["Cqctraining"],["Cutthroat"],["Cybernetic"],["Cyberneticshell"],["Cyberneticuplink"],["Debonairheart"],["Disintegrator"],["Dominance"],["Doubletrouble"],["Duet"],["Electrocharge"],["Enchanterheart"],["Enforcerheart"],["Enguarde"],["Exiles"],["Featherweights"],["Firstaidkit"],["Hexnova"],["Hextecharmory"],["Hextechheart"],["Hyperroll"],["Innovatorheart"],["Irresistiblecharm"],["Itemgrabbag"],["Keepers"],["Lifelonglearner"],["Ludensecho"],["Makeshift"],["Meditation"],["Mercenaryheart"],["Mutantheart"],["Oneforall"],["Overpowered"],["Pandora"],["Payday"],["Phalanx"],["Phonyfrontline"],["Pirates"],["Recombobulator"],["Runicshield"],["Scholarheart"],["Scrapheart"],["Secondwind"],["Selfrepair"],["Shrugoff"],["Sniperheart"],["Snipersnest"],["Socialiteheart"],["Sosmall"],["Standbehind"],["Standunited"],["Storedpower"],["Strikerheart"],["Studytheblade"],["Swiftjustice"],["Syndicateheart"],["Thrillhunt"],["Treasuretrove"],["Triforce"],["Twinshotheart"],["Underdogs"],["Unstableevolution"],["Weakspot"]];
const augmentsNameKrTier1 = [["고대의 기록 보관소"],["비전 무효화 I"],["비전 마법사 심장"],["불타는 향로"],["초월 "],["암살자 심장"],["후방 지원 I"],["전투 마법사 I"],["파란색 배터리 I "],["경호대 심장"],["난동꾼 심장"],["다른 태생 I"],["계산된 패배"],["천상의 축복 I"],["도전자 심장"],["화학적 과부하 I "],["화학공학 심장 "],["시계태엽 심장 "],["나이프의 날 I"],["자객 "],["사이버네틱 이식술 I"],["사이버네틱 외피 I"],["사이버네틱 통신 I"],["연미복 심장"],["분해기 I"],["지배 "],["문제가 두 배 I "],["이중주"],["고전압 I"],["강화술사 심장 "],["집행자 심장"],["준비 태세"],["추방자 I"],["경량급 I"],["응급처치 키트 "],["마법공학 방출 "],["마법공학 단합 "],["마법공학 심장 "],["초고속 모드"],["혁신가 심장"],["저항할 수 없는 매력"],["아이템 꾸러미 I "],["수호자 I"],["평생 학습"],["루덴의 메아리 I "],["임시변통 방어구 I"],["명상 I "],["용병 심장"],["돌연변이 심장 "],["물려주기 "],["넘치는 힘"],["판도라의 아이템"],["수금 "],["방패진 I"],["허수아비 전선 "],["해적 "],["대격변 생성기 "],["룬 보호막 I"],["학자 심장"],["고물상 심장"],["재생의 바람 I"],["자가 복구"],["강인함"],["저격수 심장"],["저격수의 은신처"],["사교계 심장"],["초소형"],["내가 지킨다"],["단결된 의지 I"],["비축 에너지"],["타격대 심장"],["도전자 단합"],["진정한 정의"],["범죄조직 심장 "],["사냥의 전율 I"],["보물창고 I "],["3에 깃든 힘 I"],["쌍발총 심장"],["약자 "],["불안정한 진화 "],["약점 I"]];
const augmentsDescTier1 = [["특성의 고서 1개를 획득합니다."],["아군 유닛들의 스킬 피해가 대상 마법 저항력의 20%를 무시하고 대상이 받는 회복 효과를 8초 동안 50% 감소시킵니다. "],["팀에 비전 마법사 1명이 추가로 포함된 것으로 간주합니다. 스웨인을 획득합니다. "],["강화술사에 의해 회복 및 보호막 효과를 얻은 아군은 전투가 끝날 때 까지 중첩 가능한 공격 속도를 45% 획득합니다. (최대 3초에 1회) 룰루를 획득합니다."],["전투 15초 후 아군 유닛의 피해량이 50% 증가합니다."],["팀에 암살자 1명이 추가로 포함된 것으로 간주합니다. 탈론을 획득합니다."],["후방 2열에서 전투를 시작하는 아군 유닛이 20%의 공격 속도를 얻습니다."],["전방 2열에서 전투를 시작하는 아군 유닛이 25의 주문력을 얻습니다. "],["아군 유닛이 스킬을 사용한 후 10의 마나를 회복합니다. "],["팀에 경호대 1명이 추가로 포함된 것으로 간주합니다. 블리츠크랭크를 획득합니다. "],["팀에 난동꾼 1명이 추가로 포함된 것으로 간주합니다. 세주아니를 획득합니다."],["활성화된 특성이 없는 아군 유닛이 300의 체력과 60%의 공격 속도를 얻습니다. "],["전투에서 패배한 후 2골드와 무료 상점 새로고침 1회를 획득합니다."],["아군 유닛이 공격과 스킬로 입힌 피해의 12%만큼 체력을 회복합니다. 초과된 회복량은 최대 300의 피해를 흡수하는 보호막으로 전환됩니다."],["팀에 도전자 1명이 추가로 포함된 것으로 간주합니다. 퀸을 획득합니다. "],["화학공학이 사망 시 폭발하여 2칸 내 적에게 최대 체력의 20%만큼 마법 피해를 입힙니다. 워윅을 획득합니다."],["팀에 화학공학 1명이 추가로 포함된 것으로 간주합니다. 워윅을 획득합니다. "],["팀에 시계태엽 1명이 추가로 포함된 것으로 간주합니다. 질리언을 획득합니다."],["전방 2열에서 전투를 시작하는 아군 유닛이 30의 공격력을 얻습니다. "],["암살자들이 첫 공격 대상의 마나를 강탈하여 대상이 스킬을 사용하기 전까지 최대 마나가 50% 증가합니다. 탈론을 획득합니다. "],["아이템을 가진 챔피언이 150의 체력과 10의 공격력을 얻습니다."],["아이템을 가진 챔피언이 150의 체력과 30의 방어력을 얻습니다."],["아이템을 장착한 아군 챔피언이 150의 체력을 얻고 매초 2의 마나를 회복합니다. "],["팀에 연미복 1명이 추가로 포함된 것으로 간주합니다. 탈론을 획득합니다."],["아군 유닛의 기본 공격이 대상 최대 체력의 1.5%에 해당하는 추가 마법 피해를 입힙니다. "],["플레이어 전투에서 승리한 후 살아남은 유닛 2명당 추가 1골드를 획득합니다."],["전장에 똑같은 챔피언이 정확히 둘 있다면 둘 다 공격력, 주문력, 방어력, 마법 저항력이 30 상승합니다. 챔피언을 3성으로 업그레이드하면 동일한 2성 챔피언 하나를 획득합니다."],["사교계 스포트라이트를 추가로 1개 소환합니다. 스포트라이트를 받은 유닛은 체력을 200 얻습니다. 세나를 획득합니다."],["아군 유닛이 치명타를 입으면 주변 적에게 60의 마법 피해를 입힙니다. (재사용 대기시간 1초) "],["팀에 강화술사 1명이 추가로 포함된 것으로 간주합니다. 룰루를 획득합니다. "],["팀에 집행자 1명이 추가로 포함된 것으로 간주합니다. 세주아니를 획득합니다."],["적이 처음으로 도전자에게 공격당하면 2.5초 동안 무장 해제됩니다. 워윅을 획득합니다. "],["인접한 아군 없이 전투를 시작하는 아군은 8초 동안 최대 체력의 30%에 해당하는 보호막을 획득합니다."],["비용이 1과 2인 챔피언이 25%의 공격 속도 및 이동 속도를 획득합니다."],["아군 유닛이 받는 모든 회복 효과와 보호막 효과가 35% 증가합니다. "],["마법공학 챔피언의 체력이 처음으로 60% 아래로 떨어지면 주변 적이 다음 스킬을 사용할 때까지 최대 마나가 50% 증가합니다. 녹턴을 획득합니다."],["모든 아군 챔피언이 마법공학 특성의 효과를 받습니다. 마법공학 유닛의 수는 변하지 않습니다. 세주아니를 획득합니다. "],["팀에 마법공학 1명이 추가로 포함된 것으로 간주합니다. 스웨인을 획득합니다."],["라운드 종료 시 가진 골드가 10보다 적다면 2골드를 획득합니다."],["팀에 혁신가 1명이 추가로 포함된 것으로 간주합니다. "],["연미복 챔피언이 받는 피해가 15% 감소합니다. 레오나를 획득합니다. "],["무작위 완성 아이템 1개를 획득합니다. "],["전투 시작 시 모든 아군 유닛이 인접한 아군에게 8초 동안 150의 피해를 흡수하는 보호막을 부여합니다. "],["전투가 끝난 후 학자가 영구적으로 2의 주문력을 얻습니다. 전투에서 생존한 경우 2의 주문력을 추가로 얻습니다. 신드라를 획득합니다. "],["아군 유닛이 스킬을 사용해 스킬 피해를 입히면 처음 적중한 대상과 그 주변 적 하나가 100의 추가 마법 피해를 입습니다. "],["아이템이 없는 아군 유닛이 35의 방어력과 마법 저항력을 얻습니다."],["아이템을 장착하지 않은 아군 유닛이 매초 5의 마나를 회복합니다. "],["팀에 용병 1명이 추가로 포함된 것으로 간주합니다. "],["팀에 돌연변이 1명이 추가로 포함된 것으로 간주합니다. 렉사이를 획득합니다."],["범죄 조직이 사망하면 다른 범죄 조직에게 15의 공격력과 주문력을 부여합니다. 애쉬를 획득합니다. "],["타격대가 2번 기본 공격할 때마다 다음 기본 공격의 치명타 확률이 75% 증가합니다. 렉사이를 획득합니다."],["무작위 조합 아이템을 획득합니다. 라운드 시작 시 대기석의 아이템이 무작위로 변합니다. (전략가의 왕관, 뒤집개, 소모품 제외) "],["전투에서 승리하고 살아남은 범죄 조직 하나당 1의 추가 골드를 획득합니다. 애쉬를 획득합니다."],["후방 2열에서 전투를 시작하는 아군 유닛이 25의 방어력 및 마법 저항력을 얻습니다."],["2개의 훈련 봇을 획득합니다. "],["용병이 적을 처치하면 50%의 확률로 1골드가 떨어집니다. 퀸을 획득합니다."],["전장에 있는 챔피언들이 비용이 1 높은 무작위 챔피언으로 영구히 바뀝니다. 자석 제거기 2개를 획득합니다. "],["전투 시작 시 비전 마법사가 8초 동안 주문력의 300%에 해당하는 보호막을 얻습니다. 스웨인을 획득합니다. "],["팀에 학자 1명이 추가로 포함된 것으로 간주합니다. 자이라를 획득합니다."],["팀에 고물상 1명이 추가로 포함된 것으로 간주합니다. 블리츠크랭크를 획득합니다. "],["전투 시작 10초 후 아군 유닛이 잃은 체력의 50%를 회복합니다. "],["혁신적 로봇이 처치되면 대상으로 지정할 수 없는 상태가 되며 혁신가가 아직 살아있으면 스스로를 복구합니다. 질리언을 획득합니다."],["난동꾼이 매초 최대 체력의 2.5%를 회복합니다. 세주아니를 획득합니다. "],["팀에 저격수 1명이 추가로 포함된 것으로 간주합니다. 애쉬를 획득합니다."],["저격수는 동일한 칸에서 전투를 시작한 라운드마다 피해량이 8% 증가합니다. (최대 +32%) 애쉬를 획득합니다. "],["팀에 사교계 1명이 추가로 포함된 것으로 간주합니다. "],["요들의 회피 확률이 25% 증가합니다. 코르키를 획득합니다. "],["경호대가 얻는 추가 방어력이 25% 증가합니다. 전투 시작 시 경호대가 바로 뒤에 있는 경호대가 아닌 아군들(경호대 제외)에게 100%의 방어력을 부여합니다. (중첩 불가) 블리츠크랭크를 획득합니다."],["아군 유닛이 전체적으로 활성화된 특성 하나당 2의 공격력과 주문력을 획득합니다. "],["아군 마법공학 챔피언들이 아군 마공학 핵의 파동에 맞을 때 마다 1의 주문력을 영구적으로 얻습니다. 스웨인을 획득합니다. "],["팀에 타격대 1명이 추가로 포함된 것으로 간주합니다. 렉사이를 획득합니다. "],["모든 아군 챔피언이 도전자 특성의 효과를 받습니다. 도전자의 수는 변하지 않습니다. 퀸을 획득합니다. "],["아군 집행자가 체력이 80% 아래인 적에게 고정 피해를 입힙니다. 세주아니를 획득합니다."],["팀에 범죄 조직 1명이 추가로 포함된 것으로 간주합니다. 자이라를 획득합니다. "],["아군 유닛이 적을 처치하면 400의 체력을 회복합니다. "],["파란색 전리품 구 1개와 회색 전리품 구 1개를 획득합니다. "],["아군 3단계 유닛의 체력이 133, 시작 마나가 13, 공격 속도가 23% 증가합니다. "],["팀에 쌍발총 1명이 추가로 포함된 것으로 간주합니다. 코르키를 획득합니다. "],["살아남은 아군 유닛이 상대보다 적다면 아군 유닛이 매초 체력을 9%만큼 회복합니다. (최대: 150)"],["돌연변이는 2성이 되면 500의 체력, 30%의 공격 속도, 30의 공격력, 30의 주문력 중 하나의 효과를 무작위로 얻습니다. 이 효과는 중첩됩니다. 렉사이를 획득합니다."],["아군 유닛이 공격 시 5초 동안 대상 방어력의 20%를 무시하고 대상이 받는 회복 효과를 50% 감소시킵니다."]];
const augmentsNameTier2 = [["RichGetRicher"],["BinaryAirdrop"],["ClearMind"],["Trade"],["SunfireBoard"],["MetabolicAccelerator"],["SalvageBin"],["PortableForge"],["CQCTraining2"],["Cybernetic2"],["Exiles2"],["Featherweights2"],["BuiltDifferent2"],["StandUnited2"],["TitanicForce"],["Makeshift2"],["CelestialBlessing2"],["ThrillOfTheHunt2"],["ArcanistEmblem"],["RunicShield2"],["SpellBlade"],["AssassinEmblem"],["Smokebomb"],["BodyguardEmblem"],["BruiserEmblem"],["ChallengerEmblem"],["ChemtechEmblem"],["ChemicalOverlord2"],["InstantInjection"],["BrokenStopwatch"],["ArmorPlating"],["MercenaryEmblem"],["GoldReserves"],["MutantEmblem"],["ScholarEmblem"],["ScrapEmblem"],["Junkyard"],["SniperEmblem"],["SyndicateEmblem"],["Sharpshooter"],["Treasure-Trove-II"],["Golden-Gifts-II"],["True-Twos-II"],["Three_s-Company-II"],["Four-Score-II"],["Component-Grab-Bag-II"],["Tiny-Titans-II"],["Jeweled-Lotus-II"],["Woodland-Trinket-II"],["Weakspot-II"],["Future-Sight-II"],["Keepers-II"],["Arcane-Nullifier-II"],["Electrocharge-II"],["Disintegrator-II"],["Ludens-Echo-II"],["Backfoot-II-B"],["Phalanx-II"],["Battlemage-II-A"],["Meditation-II-A"],["Cybernetic-Uplink-II"],["Cybernetic-Shell-II"],["Second--Wind-II"],["Archangel-II"],["Double-Trouble-II"],["Tri-Force-II"],["Thieving-Rascals-II"],["Debonair-Crest"],["VVIP-II"],["Hextech-Crest"],["Striker-Crest"],["Free...Healthcare-II"],["Concussive-Blows-II"]];
const augmentsNameKrTier2 = [["부익부"],["이중 공중 보급"],["맑은 정신"],["교환의 장"],["태양불꽃판"],["대사 촉진제"],["재활용 쓰레기통"],["휴대용 대장간"],["나이프의 날 II"],["사이버네틱 이식술 II"],["추방자 II"],["경량급 II"],["다른 태생 II"],["단결된 의지 II"],["거인의 힘"],["임시변통 방어구 II"],["천상의 축복 II"],["사냥의 전율 II"],["비전 마법사 문장"],["룬 보호막 II"],["주문 칼날"],["암살자 문장"],["연막"],["경호대 문장"],["난동꾼 문장"],["도전자 문장"],["화학공학 문장"],["화학적 과부하 II"],["즉시 주입"],["망가진 초시계"],["철갑"],["용병 문장"],["골드 비축"],["돌연변이 문장"],["학자 문장"],["고물상 문장"],["고철장"],["저격수 문장"],["범죄 조직 문장"],["명사수"],["보물창고 II"],["황금빛 선물 I"],["곱빼기"],["삼총사"],["사시사철"],["재료 꾸러미"],["꼬마 거인"],["보석 연꽃"],["숲의 장신구"],["약점 II"],["예견 I"],["수호자 II"],["비전 무효화 II"],["고전압 II"],["분해기 II"],["루덴의 메아리 II"],["후방 지원 II"],["방패진 II"],["전투 마법사 II"],["명상 II"],["사이버네틱 통신 II"],["사이버네틱 외피 II"],["재생의 바람 II"],["대천사의 포옹"],["문제가 두 배 II"],["3에 깃든 힘 II"],["조무래기 도둑들"],["연미복 문장"],["특급 VIP"],["마법공학 문장"],["타격대 문장"],["화학공학 단합"],["뇌진탕 펀치"]];
const augmentsDescTier2 = [["12골드를 획득합니다. 최대 이자가 7골드로 증가합니다."],["전투 시작 시 2개의 아이템을 장착한 챔피언에게 임시로 무작위 완성 아이템을 부여합니다."],["라운드 종료 시 대기석에 챔피언이 없다면 3의 경험치를 획득합니다."],["라운드마다 무료 상점 새로고침을 획득합니다."],["전투 시작 시 모든 적을 불태워 10초 동안 대상 최대 체력의 20%만큼 피해를 입히고 받는 회복 효과를 50% 감소시킵니다."],["전략가가 더 빠르게 움직이고, PVP 라운드가 끝날 때마다 2의 체력을 회복합니다."],["무작위 완성 아이템을 획득합니다. 챔피언을 판매하면 모든 아이템이 조합 아이템으로 분해됩니다. (전략가의 왕관 제외)"],["무기고를 열고 오른이 만든 고유 유물 3개 중 하나를 선택합니다."],["전방 2열에서 전투를 시작하는 아군 유닛이 40의 공격력을 얻습니다."],["아이템을 가진 챔피언이 250의 체력과 20의 공격력을 얻습니다."],["인접한 아군 없이 전투를 시작하는 아군은 8초 동안 최대 체력의 45%에 해당하는 보호막을 획득합니다."],["비용이 1과 2인 챔피언이 35%의 공격 속도 및 이동 속도를 획득합니다."],["활성화된 특성이 없는 아군 유닛이 400의 체력과 70%의 공격 속도를 얻습니다."],["아군 유닛이 전체적으로 활성화된 특성 하나당 3의 공격력과 주문력을 획득합니다."],["최대 체력이 1400보다 높은 아군 유닛은 최대 체력의 3%에 해당하는 공격력을 얻습니다."],["아이템이 없는 아군 유닛이 55의 방어력과 마법 저항력을 얻습니다."],["아군 유닛이 공격과 스킬로 입힌 피해의 20%만큼 체력을 회복합니다. 초과된 회복량은 최대 450의 피해를 흡수하는 보호막으로 전환됩니다."],["아군 유닛이 적을 처치하면 700의 체력을 회복합니다."],["비전 마법사 상징과 스웨인을 획득합니다."],["전투 시작 시 비전 마법사가 8초 동안 주문력의 450%에 해당하는 보호막을 얻습니다. 벡스를 획득합니다."],["스킬 사용 후 비전 마법사의 다음 공격이 주문력의 200%에 해당하는 마법 피해를 추가로 입힙니다. 스웨인을 획득합니다."],["암살자 상징과 탈론을 획득합니다."],["암살자의 체력이 처음으로 60%로 떨어지면 잠시 은신에 들어가 대상으로 지정할 수 없는 상태가 되고 모든 해로운 효과가 사라집니다. 이때 초과 피해량이 80% 감소합니다. 탈론을 획득합니다."],["경호대 상징과 다리우스를 획득합니다."],["난동꾼 상징과 세주아니를 획득합니다."],["도전자 상징과 퀸을 획득합니다."],["화학공학 상징과 워윅을 획득합니다."],["화학공학이 사망 시 폭발하여 2칸 내 적에게 최대 체력의 30%만큼 마법 피해를 입힙니다. 자크를 획득합니다."],["화학공학이 전투 시작 시 추가로 특성 효과를 발동합니다. 워윅을 획득합니다."],["전투 시작 5초 후 모든 적과 시계태엽이 아닌 유닛은 4초 동안 속박됩니다."],["거신의 체력이 50%로 떨어지면 3초 동안 무적 상태가 됩니다. 초가스를 획득합니다."],["용병 상징과 3골드를 획득합니다."],["용병이 보유한 1골드당 2%의 추가 피해를 입힙니다. (최대 +50%) 퀸을 획득합니다."],["돌연변이 상징과 카사딘을 획득합니다."],["학자 상징과 자이라를 획득합니다."],["고물상 상징과 블리츠크랭크를 획득합니다."],["고물상 특성 효과가 활성화된 3번의 전투 라운드마다 무작위 조합 아이템을 획득합니다. 이즈리얼을 획득합니다."],["저격수 상징과 애쉬를 획득합니다."],["범죄 조직 상징과 자이라를 획득합니다."],["쌍발총의 원거리 공격 및 스킬이 한 번 튕기며 60% 감소한 피해를 입힙니다. 코르키를 획득합니다."],["파란색 전리품 구 1개와 회색 전리품 구 2개를 획득합니다."],["황금 전리품 구 1개와 회색 전리품 구 2개를 획득합니다."],["무작위 2성 2단계 유닛 둘을 획득합니다."],["무작위 3단계 챔피언 4명을 획득합니다."],["무작위 4단계 챔피언 넷을 획득합니다."],["무작위 조합 아이템 3개를 획득합니다."],["전략가가 체력을 35 회복하고, 크기가 커지고, 최대 체력이 135까지 증가합니다."],["아군 스킬의 마법 및 고정 피해에 치명타가 적용될 수 있습니다. 아군 유닛의 치명타 확률이 25% 증가합니다."],["전투 시작 시 가장 공격 속도가 높은 아군 챔피언이 300의 체력을 가진 분신 둘을 생성합니다. (아이템 제외)"],["아군 유닛이 공격 시 5초 동안 대상 방어력의 40%를 무시하고 대상이 받는 회복 효과를 50% 감소시킵니다."],["다음에 싸울 상대를 알 수 있습니다. 서풍을 획득합니다."],["전투 시작 시 모든 아군 유닛이 인접한 아군에게 8초 동안 225의 피해를 흡수하는 보호막을 부여합니다."],["아군 유닛들의 스킬 피해가 대상 마법 저항력의 40%를 무시하고 대상이 받는 회복 효과를 8초 동안 50% 감소시킵니다."],["아군 유닛이 치명타를 입으면 주변 적에게 100의 마법 피해를 입힙니다. (재사용 대기시간 1초)"],["아군 유닛의 기본 공격이 대상 최대 체력의 2.5%에 해당하는 추가 마법 피해를 입힙니다."],["아군 유닛이 스킬을 사용해 스킬 피해를 입히면 처음 적중한 대상과 그 주변 적 하나가 150의 추가 마법 피해를 입습니다."],["후방 2열에서 전투를 시작하는 아군 유닛이 30%의 공격 속도를 얻습니다."],["후방 2열에서 전투를 시작하는 아군 유닛이 35의 방어력 및 마법 저항력을 얻습니다."],["전방 2열에서 전투를 시작하는 아군 유닛이 35의 주문력을 얻습니다."],["아이템을 장착하지 않은 아군 유닛이 매초 7의 마나를 회복합니다."],["아이템을 장착한 아군 챔피언이 250의 체력을 얻고 매초 3의 마나를 회복합니다."],["아이템을 장착한 아군 챔피언이 250의 체력과 45의 방어력을 얻습니다."],["전투 시작 10초 후 아군 유닛이 잃은 체력의 75%를 회복합니다."],["아군 유닛이 스킬을 사용하면 최대 마나의 25%만큼 주문력을 얻습니다."],["전장에 똑같은 챔피언이 정확히 둘 있다면 둘 다 공격력, 주문력, 방어력, 마법 저항력이 40 상승합니다. 챔피언을 3성으로 업그레이드하면 동일한 2성 챔피언 하나를 획득합니다."],["아군 3단계 유닛의 체력이 233, 시작 마나가 23, 공격 속도가 33% 증가합니다."],["차원문에서 나오는 요들이 40% 확률로 조합 아이템 하나를 장착하고 나옵니다. 룰루를 획득합니다."],["연미복 상징과 신드라를 획득합니다."],["아군이 사망하면 전투가 끝날 때 까지 연미복 VIP에게 사망한 아군 최대 체력의 30%를 부여합니다. 신드라를 획득합니다."],["마법공학 상징과 녹턴을 획득합니다."],["타격대 상징과 렉사이를 획득합니다."],["모든 아군 챔피언이 화학공학 특성의 효과를 받습니다. 화학공학 유닛의 수는 변하지 않습니다. 워윅을 획득합니다."],["타격대의 치명타가 대상을 1.5초 동안 기절시킵니다. 동일한 대상은 6초에 한 번만 기절시킬 수 있습니다. 렉사이를 획득합니다."]];
const augmentsNameTier3 = [["Windfall"],["WiseSpending"],["GrandGambler"],["ItemGrabBag2"],["BandThieves"],["NewRecruit"],["GoldenTicket"],["LevelUp"],["HighEndShopping"],["WoodlandCharm"],["SlowAndSteady"],["Exiles3"],["Featherweights3"],["CQCTraining3"],["Cybernetic3"],["BuiltDifferent3"],["StandUnited3"],["Makeshift3"],["CelestialBlessing3"],["RunicShield3"],["ArcanistSoul"],["AssassinSoul"],["BodyguardSoul"],["BruiserSoul"],["ChallengerSoul"],["ChemicalOverlord3"],["ChemtechSoul"],["ClockworkSoul"],["EnchanterSoul"],["EnforcerSoul"],["InnovatorSoul"],["MercenarySoul"],["MutantSoul"],["ScholarSoul"],["ScrapSoul"],["SniperSoul"],["SocialiteSoul"],["SyndicateSoul"],["TwinshotSoul"],["Golden-Gifts-III"],["Treasure-Trove-III"],["High-Five"],["RadiantRelic-III"],["The-Golden-Egg-III"],["Verdant-Veil-III"],["Weakspot-III"],["Future-Sight-III"],["Blue-Battery-III"],["Arcane-Nullifier-III"],["Electrocharge-III"],["Disintegrator-III"],["Ludens-Echo-III"],["Backfoot-III-B"],["Phalanx-III"],["Battlemage-III-A"],["Meditation-III-A"],["Cybernetic-Uplink-III"],["Cybernetic-Shell-III"],["Double-Trouble-III"],["Tri-Force-III"],["Debonair-Crown"],["Hextech-Crown"],["Striker-Crown"],["ShareSpotlight"]];
const augmentsNameKrTier3 = [["뜻밖의 횡재"],["현명한 소비"],["큰손"],["아이템 꾸러미 II"],["도적 무리"],["신병"],["황금 티켓"],["레벨 업!"],["품격있는 쇼핑"],["숲의 부적"],["진보의 행진"],["추방자 III"],["경량급 III"],["나이프의 날 III"],["사이버네틱 이식술 III"],["다른 태생 III"],["단결된 의지 III"],["임시변통 방어구 III"],["천상의 축복 III"],["룬 보호막 III"],["비전 마법사 왕관"],["암살자 왕관"],["경호대 왕관"],["난동꾼 왕관"],["도전자 왕관"],["화학적 과부하 III"],["화학공학 왕관"],["시계태엽 영혼"],["강화술사 영혼"],["집행자 영혼"],["혁신가 영혼"],["용병 영혼"],["돌연변이 왕관"],["학자 영혼"],["고물상 영혼"],["저격수 왕관"],["사교계 영혼"],["범죄 조직 왕관"],["쌍발총 영혼"],["황금빛 선물 II"],["보물창고 III"],["하이파이브"],["찬란한 유물"],["황금 알"],["신록의 장막"],["약점 III"],["예견 II"],["파란색 배터리 II"],["비전 무효화 III"],["고전압 III"],["분해기 III"],["루덴의 메아리 III"],["후방 지원 III"],["방패진 III"],["전투 마법사 III"],["명상 III"],["사이버네틱 통신 III"],["사이버네틱 외피 III"],["문제가 두 배 III"],["3에 깃든 힘 III"],["연미복 왕관"],["마법공학 왕관"],["타격대 왕관"],["스포트라이트 공유"]];
const augmentsDescTier3 = [["현재 보유한 증강 수에 비례해 골드를 획득합니다. 0 = 18골드, 1 = 30골드, 2 = 45골드"],["4골드를 획득합니다. 상점을 새로고침하면 2의 경험치를 획득합니다."],["3개의 사기 주사위와 8골드를 획득합니다."],["무작위 완성 아이템 2개와 재조합기를 획득합니다."],["도적의 장갑 2개를 획득합니다."],["최대 팀 규모가 1 증가합니다."],["상점을 새로고침할 때마다 40% 확률로 무료 새로고침을 획득합니다."],["경험치를 구매하면 3의 추가 경험치를 얻습니다. 이제 10레벨에 도달할 수 있습니다."],["현재 플레이어 레벨보다 1레벨 높을 때 나오는 챔피언이 상점에 등장합니다. 5골드를 획득합니다."],["전투 시작 시 가장 체력이 높은 아군 챔피언이 1600의 체력을 가진 분신 하나를 생성합니다. (아이템 제외)"],["라운드마다 추가로 5의 경험치를 획득합니다. 더는 골드를 사용해 레벨 업할 수 없습니다."],["인접한 아군 없이 전투를 시작하는 아군은 8초 동안 최대 체력의 70%에 해당하는 보호막을 획득합니다."],["비용이 1과 2인 챔피언이 55%의 공격 속도 및 이동 속도를 획득합니다."],["전방 2열에서 전투를 시작하는 아군 유닛이 55의 공격력을 얻습니다."],["아이템을 가진 챔피언이 350의 체력과 30의 공격력을 얻습니다."],["활성화된 특성이 없는 아군 유닛이 500의 체력과 80%의 공격 속도를 얻습니다."],["아군 유닛이 전체적으로 활성화된 특성 하나당 5의 공격력과 주문력을 획득합니다."],["아이템이 없는 아군 유닛이 80의 방어력과 마법 저항력을 얻습니다."],["아군 유닛이 공격과 스킬로 입힌 피해의 35%만큼 체력을 회복합니다. 초과된 회복량은 최대 600의 피해를 흡수하는 보호막으로 전환됩니다."],["전투 시작 시 비전 마법사가 주문력의 600%에 해당하는 보호막을 8초 동안 얻습니다."],["비전 마법사 상징 2개를 획득합니다."],["암살자 상징 2개를 획득합니다."],["경호대 상징 2개를 획득합니다."],["난동꾼 상징 2개를 획득합니다."],["도전자 상징 2개를 획득합니다."],["화학공학이 사망 시 폭발하여 2칸 내 적에게 적 최대 체력의 40%만큼 마법 피해를 입힙니다."],["화학공학 상징 2개를 획득합니다."],["팀에 시계태엽 2명이 추가로 포함된 것으로 간주합니다. 8골드를 획득합니다."],["팀에 강화술사 2명이 추가로 포함된 것으로 간주합니다. 8골드를 획득합니다."],["팀에 집행자 2명이 추가로 포함된 것으로 간주합니다. 8골드를 획득합니다."],["팀에 혁신가 2명이 추가로 포함된 것으로 간주합니다."],["팀에 용병 2명이 추가로 포함된 것으로 간주합니다."],["돌연변이 상징 2개를 획득합니다."],["팀에 학자 2명이 추가로 포함된 것으로 간주합니다. 8골드를 획득합니다."],["팀에 고물상 2명이 추가로 포함된 것으로 간주합니다. 8골드를 획득합니다."],["저격수 상징 2개를 획득합니다."],["팀에 사교계 2명이 추가로 포함된 것으로 간주합니다."],["범죄 조직 상징 2개를 획득합니다."],["팀에 쌍발총 2명이 추가로 포함된 것으로 간주합니다. 8골드를 획득합니다."],["황금 전리품 구 2개와 회색 전리품 구 3개를 획득합니다."],["파란색 전리품 구 3개와 회색 전리품 구 2개를 획득합니다."],["무작위 5단계 챔피언 5명을 획득합니다."],["무기고를 열어 찬란한 아이템 4가지 중 1개를 선택합니다."],["7턴 안에 부화하는 거대한 황금 알을 획득합니다."],["아군 유닛이 전투 시작 후 15초 동안 군중 제어 효과에 면역이 됩니다."],["아군 유닛이 공격 시 5초 동안 대상 방어력의 60%를 무시하고 대상이 받는 회복 효과를 50% 감소시킵니다."],["다음에 싸울 상대를 알 수 있습니다. 찬란한 서풍을 획득합니다."],["아군 유닛이 스킬을 사용한 후 20의 마나를 회복합니다."],["아군 유닛들의 스킬 피해가 대상 마법 저항력의 60%를 무시하고 대상이 받는 회복 효과를 8초 동안 50% 감소시킵니다."],["아군 유닛이 치명타를 입으면 주변 적에게 160의 마법 피해를 입힙니다. (재사용 대기시간 1초)"],["아군 유닛의 기본 공격이 대상 최대 체력의 4%에 해당하는 추가 마법 피해를 입힙니다."],["아군 유닛이 스킬을 사용해 스킬 피해를 입히면 처음 적중한 대상과 그 주변 적 하나가 200의 추가 마법 피해를 입습니다."],["후방 2열에서 전투를 시작하는 아군 유닛이 45%의 공격 속도를 얻습니다."],["후방 2열에서 전투를 시작하는 아군 유닛이 50의 방어력 및 마법 저항력을 얻습니다."],["전방 2열에서 전투를 시작하는 아군 유닛이 50의 주문력을 얻습니다."],["아이템을 장착하지 않은 아군 유닛이 매초 10의 마나를 회복합니다."],["아이템을 장착한 아군 챔피언이 350의 체력을 얻고 매초 4의 마나를 회복합니다."],["아이템을 장착한 아군 챔피언이 350의 체력과 60의 방어력을 얻습니다."],["전장에 똑같은 챔피언이 정확히 둘 있다면 둘 다 공격력, 주문력, 방어력, 마법 저항력이 50 상승합니다. 챔피언을 3성으로 업그레이드하면 동일한 2성 챔피언 하나를 획득합니다."],["아군 3단계 유닛의 체력이 333, 시작 마나가 33, 공격 속도가 43% 증가합니다."],["연미복 상징을 2개 획득합니다."],["마법공학 상징을 2개 획득합니다."],["타격대 상징을 2개 획득합니다."],["전투 시작 시 스포트라이트에 인접한 아군들이 스포트라이트 효과의 125%를 얻습니다. 세나를 획득합니다."]];
const tier1Button = document.getElementById("btnradio1");
const tier2Button = document.getElementById("btnradio2");
const tier3Button = document.getElementById("btnradio3");
const searchAugment = document.getElementById("searchAugment");

function showTierAugmentsList(Tier){
    let innerHtml = "";

    if(Tier == 1) {
        for (let i = 0; i < augmentsNameTier1.length; i++) {
            innerHtml += "<div class='row'><div class='col-md-1'><a href=''><img src='/img/tft/augment/tier1/" + augmentsNameTier1[i] + ".png' class='augmentListImage'></a></div>";
            innerHtml += "<div class='text-white col-md-9'>";
            innerHtml += "<h5>" + augmentsNameKrTier1[i] + "</h5>";
            innerHtml += "<p>" + augmentsDescTier1[i] + "</p></div></div>";
        }
    }else if(Tier == 2){
        for (let i = 0; i < augmentsNameTier2.length; i++) {
            innerHtml += "<div class='row'><div class='col-md-1'><a href=''><img src='/img/tft/augment/tier2/" + augmentsNameTier2[i] + ".png' class='augmentListImage'></a></div>";
            innerHtml += "<div class='text-white col-md-9'>";
            innerHtml += "<h5>" + augmentsNameKrTier2[i] + "</h5>";
            innerHtml += "<p>" + augmentsDescTier2[i] + "</p></div></div>";
        }
    }else if(Tier == 3){
        for (let i = 0; i < augmentsNameTier3.length; i++) {
            innerHtml += "<div class='row'><div class='col-md-1'><a href=''><img src='/img/tft/augment/tier3/" + augmentsNameTier3[i] + ".png' class='augmentListImage'></a></div>";
            innerHtml += "<div class='text-white col-md-9'>";
            innerHtml += "<h5>" + augmentsNameKrTier3[i] + "</h5>";
            innerHtml += "<p>" + augmentsDescTier3[i] + "</p></div></div>";
        }
    }
    document.getElementById("augmentsList").innerHTML = innerHtml;
}

function eventSearchAugment(e) {
    let search = e.target.value;
    let searchInnerHtml = "";

    if(search != "") {
        tier1Button.checked = false;
        tier2Button.checked = false;
        tier3Button.checked = false;

        for (let i = 0; i < augmentsNameTier1.length; i++) {
            if (augmentsNameKrTier1[i][0].indexOf(search) != -1) {
                searchInnerHtml += "<div class='row'><div class='col-md-1'><a href=''><img src='/img/tft/augment/tier1/" + augmentsNameTier1[i] + ".png' class='augmentListImage'></a></div>";
                searchInnerHtml += "<div class='text-white col-md-9'>";
                searchInnerHtml += "<h5>" + augmentsNameKrTier1[i] + "</h5>";
                searchInnerHtml += "<p>" + augmentsDescTier1[i] + "</p></div></div>";
            }
        }
        for (let j = 0; j < augmentsNameTier2.length; j++) {
            if (augmentsNameKrTier2[j][0].indexOf(search) != -1) {
                searchInnerHtml += "<div class='row'><div class='col-md-1'><a href=''><img src='/img/tft/augment/tier2/" + augmentsNameTier2[j] + ".png' class='augmentListImage'></a></div>";
                searchInnerHtml += "<div class='text-white col-md-9'>";
                searchInnerHtml += "<h5>" + augmentsNameKrTier2[j] + "</h5>";
                searchInnerHtml += "<p>" + augmentsDescTier2[j] + "</p></div></div>";
            }
        }
        for (let k = 0; k < augmentsNameTier3.length; k++) {
            if (augmentsNameKrTier3[k][0].indexOf(search) != -1) {
                searchInnerHtml += "<div class='row'><div class='col-md-1'><a href=''><img src='/img/tft/augment/tier3/" + augmentsNameTier3[k] + ".png' class='augmentListImage'></a></div>";
                searchInnerHtml += "<div class='text-white col-md-9'>";
                searchInnerHtml += "<h5>" + augmentsNameKrTier3[k] + "</h5>";
                searchInnerHtml += "<p>" + augmentsDescTier3[k] + "</p></div></div>";
            }
        }
        document.getElementById("augmentsList").innerHTML = searchInnerHtml;
    } else {
        tier1Button.checked = true;
        showTierAugmentsList(1);
    }
}

tier1Button.addEventListener('click',function (){
    showTierAugmentsList(1);
});
tier2Button.addEventListener('click',function (){
    showTierAugmentsList(2);
});
tier3Button.addEventListener('click',function (){
    showTierAugmentsList(3);
});
searchAugment.addEventListener('input', eventSearchAugment);
showTierAugmentsList(1);