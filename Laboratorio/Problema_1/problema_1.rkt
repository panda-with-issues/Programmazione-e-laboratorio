;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define femminile?   ; val: bool
  (lambda (str)      ; str: stringa, sostantivo italiano
    (let ((last-char (string-ref str (- (string-length str) 1))))
      (if (or (char=? last-char #\a)
              (char=? last-char #\e)
              )
          #t
          #f
          )
      )))

(define plurale?    ; val: bool
  (lambda (str)     ; str: stringa, sostantivo italiano
    (let ((last-char (string-ref str (- (string-length str) 1))))
      (if (or (char=? last-char #\e)
              (char=? last-char #\i))
          #t
          #f
          )
      )))

(define prima-con?   ; val: bool
  (lambda (v)        ; v: stringa, verbo italiano all'infinito
    (if (char=?
           (string-ref v (- (string-length v) 3))
           #\a
           )
        #t
        #f
       )
    ))

(define 3-pers      ; val: stringa, verbo coniugato alla terza persona singolare o plurale
  (lambda (v sogg)  ; v: stringa, verbo italiano all'infinito, sogg: stringa, soggetto del verbo
    (let ((radice (substring v 0 (- (string-length v) 3)))
          (v-wo-re (substring v 0 (- (string-length v) 2)))
          )
      (cond ((and (prima-con? v) (plurale? sogg))
             (string-append v-wo-re "no")
             )
            ((prima-con? v)
             v-wo-re
             )
            ((plurale? sogg)
             (string-append radice "ono")
             )
            (else
             (string-append radice"e")
             )
        )
      )))

(define make-art   ; val: stringa, sostantivo italiano preceduto dal giusto articolo
  (lambda (s)      ; s: stringa, sostantivo italiano
    (string-append (cond ((and (femminile? s) (plurale? s)) "le ")
                         ((femminile? s) "la ")
                         ((plurale? s) "i ")
                         (else "il ")
                         )
                   s
                   )
    ))
  

(define frase      ; val: stringa con articoli e verbo coniugato correttamente
  (lambda (s v o)  ; s, v, o: stringhe; s: sostantivo italiano, v: verbo italiano all'infinito, o: sostantivo italiano
    (string-append
       (make-art s)
       " "
       (3-pers v s)
       " "
       (make-art o)
       "."
       )
    ))

;; test

(frase "gatto" "cacciare" "topi")
(frase "mucca" "mangiare" "fieno")
(frase "sorelle" "leggere" "novella")
(frase "bambini" "amare" "favole")
(frase "musicisti" "suonare" "pianoforti")
(frase "cuoco" "friggere" "patate")
(frase "camerieri" "servire" "clienti")
(frase "mamma" "chiamare" "figlie")