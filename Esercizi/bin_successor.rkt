;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname bin_successor) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define bin-succ  ; val: string composed by chars in [0, 1]
  (lambda (bin)   ; bin: same as val
    (let ((last-idx (- (string-length bin) 1)))
      (let ((head (substring bin 0 last-idx))
            (last-d (string-ref bin last-idx))
            )
        ; base cases
        (cond ((string=? bin "1")
              "10"
              )
              ((char=? last-d #\0)
              (string-append head "1")
              )
              (else
               ; recursive step
               (string-append
                (bin-succ head)
                "0"
                )
               )
          )
    ))))

;; test
(bin-succ "0")      ; "1"
(bin-succ "1")      ; "10"
(bin-succ "10")     ; "11"
(bin-succ "11")     ; "100"
(bin-succ "100011") ; "100100"