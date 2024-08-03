// Este es el componente principal para renderizar otros componentes.

import React from 'react';
import PostList from './components/PostList';
import CreatePost from './components/CreatePost';

function App() {
    return (
        <div className="App">
            <h1>Blog</h1>
            <CreatePost />
            <PostList />
        </div>
    );
}

export default App;

