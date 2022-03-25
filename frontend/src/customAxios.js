import axios from 'axios'; // 액시오스

export default function customAxios(callback) {
    const headers = {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'Accept': '*/*'
    }
    axios.defaults.headers.post = null
    axios.get('http://192.168.0.20:8080', {headers})
        .then(res => { // headers: {…} 로 들어감.
            console.log('send ok', res.data)
            callback(res.data)
        })

}