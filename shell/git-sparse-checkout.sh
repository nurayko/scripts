REMOTE_URL=""
CHECKOUT_DIRS=""
git init .
git remote add origin $REMOTE_URL
git sparse-checkout set $CHECKOUT_DIRS
git config --replace-all remote.origin.fetch +refs/heads/*:refs/remotes/origin/*
git config --add remote.origin.fetch HEAD:refs/remotes/origin/HEAD
git config --replace-all extensions.partialclone true
git config --replace-all remote.origin.partialclonefilter tree:0
git fetch --all --prune --prune-tags
git checkout origin/main