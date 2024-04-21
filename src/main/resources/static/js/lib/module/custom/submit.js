function collectData(id){
    let elementList = document.getElementById(id).querySelectorAll('input, select, textarea');

    let pair = {};
    elementList.forEach(function(element) {
        const type = element.getAttribute("type")
        if (type === 'checkbox' || type === 'radio') {
            pair[element.name] = element.checked;
        } else {
            pair[element.name] = element.value;
        }
    });
    console.log(pair)

    return pair;
}