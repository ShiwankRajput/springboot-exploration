// Here is the main logic of the button that we will use in our Gmail, this button will 
// be connected to the main backend logic.

// mutation observer helps in monitoring dom elements, when we open compose or reply of a email 
// then we try to add a button which is possibel through monitoring hence MutationObserver
// Whenever the change occurs in the page the callback function runs.

console.log("Email Writer");

function getEmailContent() {
    const selectors = ['.h7', '.a3s.aiL', '.gmail_quote', '[role="presentation"]'];
    for (const selector of selectors) {
        const content = document.querySelector(selector);
        if (content) return content.innerHTML.trim();
    }
    return '';
}

function findComposeToolbar() {
    const selectors = ['.btC', '.aDh', '[role="toolbar"]', '.gU.Up'];
    for (const selector of selectors) {
        const toolbar = document.querySelector(selector);
        if (toolbar) return toolbar;
    }
    return null;
}

function createAIButton() {
    const button = document.createElement('div');
    button.className = 'T-I J-J5_Ji aoO v7 T-I-atl L3';
    button.style.marginRight = '8px';
    button.innerHTML = 'AI Reply';
    button.setAttribute('role', 'button');
    button.setAttribute('data-tooltip', 'Generate AI Reply');
    button.classList.add('ai-reply-button'); 
    return button;
}

function injectButton() {
    const existingButton = document.querySelector('.ai-reply-button');
    if (existingButton) existingButton.remove();

    const toolbar = findComposeToolbar();
    if (!toolbar) {
        console.log("Toolbar not found");
        return;
    }

    console.log("Toolbar found");
    const button = createAIButton();

    button.addEventListener('click', async () => {
        try {
            button.innerHTML = 'Generating...';
            button.disabled = true;

            let emailContent = getEmailContent();
            // escape for JSON
            emailContent = emailContent.replace(/\\/g, "\\\\").replace(/"/g, '\\"').replace(/\n/g, "\\n");

            const response = await fetch('http://localhost:8080/api/email/generate', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ emailContent, tone: 'professional' })
            });

            if (!response.ok) {
                const text = await response.text();
                console.error("Backend error response:", text);
                throw new Error(`API request failed: ${text}`);
            }

            const generatedReply = await response.text();
            const composeBox = document.querySelector('div[role="textbox"]');

            if (composeBox) {
                composeBox.focus();
                document.execCommand('insertText', false, generatedReply);
            } else {
                console.error("Compose box not found!");
            }

        } catch (error) {
            console.error("Error generating AI reply:", error);
        } finally {
            button.innerHTML = 'AI Reply';
            button.disabled = false;
        }
    });

    toolbar.insertBefore(button, toolbar.firstChild);
}

// Observe DOM changes to detect compose windows
const observer = new MutationObserver((mutations) => {
    for (const mutation of mutations) {
        const addedNodes = Array.from(mutation.addedNodes);
        const hasCompose = addedNodes.some(node =>
            node.nodeType === Node.ELEMENT_NODE &&
            (node.matches('.aDh, .btC, [role="dialog"]') ||
             node.querySelector('.aDh, .btC, [role="dialog"]'))
        );
        if (hasCompose) {
            console.log("Compose Window Detected");
            setTimeout(injectButton, 500);
        }
    }
});

observer.observe(document.body, { childList: true, subtree: true });