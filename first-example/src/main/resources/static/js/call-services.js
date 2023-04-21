function getAllPeople() {
    fetch('/api/v1/people')
        .then(response => response.json())
        .then(json => {
            document.querySelector('#allPeopleResult').textContent = JSON.stringify(json, null, 2);
        })
        .catch(error => console.log(error));
}

function getPersonById(id) {
    fetch(`/api/v1/people/${id}`)
        .then(response => response.json())
        .then(json => {
            document.querySelector('#personByIdResult').textContent = JSON.stringify(json, null, 2);
        })
        .catch(error => console.log(error));
}

window.addEventListener('load', () => {

    document.querySelector('#getAllPeopleButton').addEventListener('click', () => getAllPeople())
    document.querySelector('#clearAllPeopleResultButton').addEventListener('click', () => {
        document.querySelector('#allPeopleResult').textContent = '';
    })
    document.querySelector('#getPersonByIdButton').addEventListener('click', () => {
        let personId = Number(document.querySelector('#personIdField').value);
        getPersonById(personId)
    });
    document.querySelector('#clearPersonByIdResultButton').addEventListener('click', () => {
        document.querySelector('#personByIdResult').textContent = '';
    })
    document.querySelector('#personIdField').addEventListener('input', event => {
        document.querySelector('#getPersonByIdLink').href = `/api/v1/people/${event.target.value}`
    })

})