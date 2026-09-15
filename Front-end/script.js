const baseURL = window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1' 
    ? 'http://localhost:8080'
    : 'https://sua-api-no-render.onrender.com';

const posterImg = document.getElementById('poster');
const tituloObra = document.getElementById('titulo-obra');
const fraseTexto = document.getElementById('frase');
const personagemTexto = document.getElementById('personagem');
const contextoTexto = document.getElementById('contexto');
const selectTema = document.getElementById('vibe-select');
const btnOutraFrase = document.getElementById('btn-outra-frase');
const btnGerarIA = document.getElementById('btn-gerar-ia');
const btnOuvir = document.getElementById('btn-ouvir');
const btnParar = document.getElementById('btn-parar');

function atualizarTela(dados) {
    const titulo = dados.titulo || dados.nome;
    tituloObra.textContent = titulo;
    fraseTexto.textContent = `"${dados.frase}"`;
    personagemTexto.textContent = dados.personagem;
    contextoTexto.textContent = dados.contexto;
    
    const posterValido = dados.poster && dados.poster !== 'N/A';
    posterImg.src = posterValido 
        ? dados.poster 
        : 'https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&w=600&q=80';
    posterImg.alt = `Pôster de ${titulo}`;
}

const frasesExemplo = [
    {
        titulo: "Rocky Balboa",
        frase: "Não importa o quanto você bate, mas sim o quanto aguenta apanhar e continuar avançando.",
        personagem: "Rocky Balboa",
        contexto: "Discurso motivacional de Rocky para seu filho na rua, sobre a dureza da vida.",
        poster: "https://m.media-amazon.com/images/M/MV5BMzY1ZTNlMmItN2U0ZS00Y2JkLWI0YmEtNWUyMWJiMGUzMjNiXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"
    },
    {
        titulo: "Breaking Bad",
        frase: "Eu não estou em perigo, Skyler. Eu sou o perigo!",
        personagem: "Walter White",
        contexto: "Walter confrontando Skyler após ela demonstrar medo de alguém bater na porta de casa.",
        poster: "https://m.media-amazon.com/images/M/MV5BMzU5ZGYzNmQtMTdhYy00OGRiLTg0NmQtYjVjNzliZTg1ZGE4XkEyXkFqcGc@._V1_SX300.jpg"
    },
    {
        titulo: "The Matrix",
        frase: "Infelizmente é impossível dizer o que é a Matrix. Você tem que ver por si mesmo.",
        personagem: "Morpheus",
        contexto: "Morpheus oferecendo as pílulas azul e vermelha para Neo no hotel abandonado.",
        poster: "https://m.media-amazon.com/images/M/MV5BN2NmN2VhMTQtMDNiOS00NDlhLTliMjgtODE2ZTY0ODQyNDRhXkEyXkFqcGc@._V1_SX300.jpg"
    }
];

async function buscarFrase(endpoint = '') {
    const generoSelecionado = selectTema.value;
    let url = `${baseURL}/obra${endpoint}`;
    
    if (generoSelecionado && endpoint === '') {
        url = `${baseURL}/obra/genero/${encodeURIComponent(generoSelecionado)}`;
    }

    try {
        const resposta = await fetch(url);
        if (!resposta.ok) throw new Error('Servidor offline');
        const dados = await resposta.json();
        atualizarTela(dados);
    } catch (erro) {
        const aleatorio = frasesExemplo[Math.floor(Math.random() * frasesExemplo.length)];
        atualizarTela(aleatorio);
    }
}

if ('speechSynthesis' in window) {
    window.speechSynthesis.onvoiceschanged = () => window.speechSynthesis.getVoices();
}

function obterMelhorVozPtBR() {
    const vozes = window.speechSynthesis.getVoices();
    if (!vozes || vozes.length === 0) return null;

    const vozNatural = vozes.find(v => (v.lang === 'pt-BR' || v.lang === 'pt_BR') && v.name.toLowerCase().includes('natural'));
    if (vozNatural) return vozNatural;

    const vozGoogle = vozes.find(v => (v.lang === 'pt-BR' || v.lang === 'pt_BR') && v.name.toLowerCase().includes('google'));
    if (vozGoogle) return vozGoogle;

    const vozPtBr = vozes.find(v => v.lang === 'pt-BR' || v.lang === 'pt_BR');
    if (vozPtBr) return vozPtBr;

    return vozes.find(v => v.lang.startsWith('pt')) || null;
}

btnOuvir.addEventListener('click', () => {
    if (!('speechSynthesis' in window)) {
        alert("Seu navegador não suporta leitura de áudio.");
        return;
    }
    
    window.speechSynthesis.cancel();
    const textoParaFalar = `${fraseTexto.textContent}. Citado por ${personagemTexto.textContent}. Contexto da cena: ${contextoTexto.textContent}`;
    
    const fala = new SpeechSynthesisUtterance(textoParaFalar);
    const voz = obterMelhorVozPtBR();
    if (voz) {
        fala.voice = voz;
    }
    
    fala.lang = 'pt-BR';
    fala.rate = 0.92;
    fala.pitch = 1.0;
    
    fala.onstart = () => {
        btnParar.style.display = 'inline-block';
    };
    fala.onend = () => {
        btnParar.style.display = 'none';
    };
    fala.onerror = () => {
        btnParar.style.display = 'none';
    };
    
    window.speechSynthesis.speak(fala);
});

btnParar.addEventListener('click', () => {
    window.speechSynthesis.cancel();
    btnParar.style.display = 'none';
});

btnOutraFrase.addEventListener('click', () => buscarFrase(''));

btnGerarIA.addEventListener('click', async () => {
    const textoOriginal = btnGerarIA.textContent;
    btnGerarIA.textContent = 'Carregando frase...';
    btnGerarIA.disabled = true;

    try {
        await buscarFrase('/frase');
    } finally {
        btnGerarIA.textContent = textoOriginal;
        btnGerarIA.disabled = false;
    }
});

selectTema.addEventListener('change', () => buscarFrase(''));

buscarFrase();