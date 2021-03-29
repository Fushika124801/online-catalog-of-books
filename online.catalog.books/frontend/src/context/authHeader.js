export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
  
    if (user) {
      return { 'Authorization': user.type + " " + user.token };
    } else {
      return null;
    }
  }