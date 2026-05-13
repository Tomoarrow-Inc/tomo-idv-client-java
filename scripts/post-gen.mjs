/**
 * Post-generation fixup for tomo-idv-client-java.
 * Copies generated Java sources/docs from the temporary OpenAPI output
 * directory into the manually maintained Gradle project.
 */
import {
  cpSync,
  existsSync,
  mkdirSync,
  readFileSync,
  readdirSync,
  rmSync,
  statSync,
  writeFileSync,
} from 'node:fs';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = dirname(fileURLToPath(import.meta.url));
const repoRoot = join(__dirname, '..');
const tempRoot = join(repoRoot, '.openapi-generator-tmp');
const generatedSource = join(
  tempRoot,
  'src',
  'main',
  'java',
  'com',
  'tomoarrow',
  'idv',
  'client',
  'generated',
);
const generatedDest = join(
  repoRoot,
  'src',
  'main',
  'java',
  'com',
  'tomoarrow',
  'idv',
  'client',
  'generated',
);
const docsSource = join(tempRoot, 'docs');
const openapiSource = join(tempRoot, 'api', 'openapi.yaml');
const docsDest = join(repoRoot, 'generated-docs');

if (!existsSync(generatedSource)) {
  throw new Error(`Generated Java package not found: ${generatedSource}`);
}

mkdirSync(join(repoRoot, 'src', 'main', 'java', 'com', 'tomoarrow', 'idv', 'client'), {
  recursive: true,
});
cpSync(generatedSource, generatedDest, { recursive: true });
fixJavaGeneratorMapAliasHashCode(generatedDest);
console.log(`Copied generated package to ${generatedDest}`);

rmSync(docsDest, { recursive: true, force: true });
mkdirSync(docsDest, { recursive: true });
if (existsSync(docsSource)) {
  cpSync(docsSource, join(docsDest, 'docs'), { recursive: true });
}
if (existsSync(openapiSource)) {
  cpSync(openapiSource, join(docsDest, 'openapi.yaml'));
}
console.log(`Copied generated docs to ${docsDest}`);

rmSync(tempRoot, { recursive: true, force: true });

function fixJavaGeneratorMapAliasHashCode(dir) {
  for (const entry of readdirSync(dir)) {
    const path = join(dir, entry);
    if (statSync(path).isDirectory()) {
      fixJavaGeneratorMapAliasHashCode(path);
      continue;
    }
    if (!entry.endsWith('.java')) {
      continue;
    }

    const original = readFileSync(path, 'utf8');
    const fixed = original.replaceAll(
      'Objects.hash(, additionalProperties)',
      'Objects.hash(additionalProperties)',
    );
    if (fixed !== original) {
      writeFileSync(path, fixed);
    }
  }
}
