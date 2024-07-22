import http from 'k6/http';
import { sleep, check } from 'k6';

export let options = {
    vus: 10,
    duration: '30s',
};

export default function () {
    let res = http.get('https://jsonplaceholder.typicode.com/posts/1');
    check(res, {
        'is status 200': (r) => r.status === 200,
    });
    sleep(1);
}
