/**
 * JSON 통신
 * @param object
 *    url : {url},
 *    method : {Http method},
 *    data : {json object}
 * @returns {Promise<unknown>}
 */
function requestJsonAjaxPromise(object){

    object.dataType =  "json"
    object.contentType = 'application/json'

    if (object.method?.toLowerCase() !== 'get' && object.type?.toLowerCase() !== 'get'){
        if (object.data){
            object.data = JSON.stringify(object.data)
        }
    }

    return requestAjaxPromise(object)
}

/**
 * JSON 통신
 * Ajax에서 반환하는 불필요한 경로를 건너뛰고 데이터를 가져올 수 있도록 변경
 * - response.data -> response
 * - error.responseJSON.data -> error
 *
 * @param object
 *    url : {url},
 *    method : {Http method},
 *    data : {json object}
 * @returns {Promise<unknown>}
 */
function requestJsonAjaxPromiseV2Form(object){

    object.dataType =  "json"
    object.contentType = 'application/json'

    if (object.method?.toLowerCase() !== 'get' && object.type?.toLowerCase() !== 'get'){
        if (object.data){
            object.data = JSON.stringify(object.data)
        }
    }

    return requestAjaxPromiseResponseForm(object)
}

/**
 * Image 통신
 * @param object
 *    url : {url},
 *    method : {Http method},
 *    data : {FormData}
 * @returns {Promise<unknown>}
 */
function requestMultipartAjaxPromise(object){
    object.dataType = "json"
    object.enctype = "multipart/form-data"
    object.contentType = false
    object.processData = false

    return requestAjaxPromise(object)
}

function requestAjaxPromise(object) {

    return new Promise((resolve, reject) => {
        $.ajax(
            object
        )
            .done(function (response) {
                resolve(response)
            })
            .fail(function (error) {
                reject(error)
            })
            .always(function () {

            })
    });
}

/**
 * response -> response.data
 * error -> error.responseJSON.data
 * @param object
 * @returns {Promise<unknown>}
 */
function requestAjaxPromiseResponseForm(object) {

    return new Promise((resolve, reject) => {
        $.ajax(
            object
        )
            .done(function (response) {
                resolve(response.data)
            })
            .fail(function (error) {
                reject(error.responseJSON.data)
            })
            .always(function () {

            })
    });
}

/**
 * select 옵션 추가
 * <select id='target'></select>
 * 
 * requestSelectOption('target', {
 *     url : '/api/...',
 *     method : 'GET',
 * })
 *
 * @param id
 * @param object
 */
function requestSelectOption(selector, object){

    requestJsonAjaxPromise(object)
        .then(function(response){

            document.querySelectorAll(selector).forEach(function (element){

                if (element.options.length <= 1){
                    response.data.contents.forEach(function (data){
                        let optionElement = document.createElement("option");
                        optionElement.text = data.text;
                        optionElement.value = data.value;
                        element.appendChild(optionElement)
                    })
                }

            })

    }).catch(function(error){
        alert("옵션을 가져오지 못하였습니다.")
    })
}

